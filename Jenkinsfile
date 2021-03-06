pipeline {
    agent any
    environment {
        reportDir = 'build/reports'
        javaDir = 'src/main/java'
        resourcesDir = 'src/main/resources'
        testReportDir = 'build/test-results/test'
        jacocoReportDir = 'build/jacoco' 
        javadocDir = 'build/docs/javadoc'
        libsDir = 'build/libs'
        appName = 'AddressSearcher'
        appVersion = '0.1.0'
    }
    
    stages {
        stage('チェックアウト') {
            steps {
                deleteDir()
                checkout scm
                archiveArtifacts "Jenkinsfile"
                archiveArtifacts "build.gradle"
                script {
                    // permission deniedで怒られないために実行権限を付与する
                    if(isUnix()) {
                        sh 'chmod +x gradlew'
                    }
                }
                gradlew 'clean'
            }
        }
        
        stage('コンパイル') {
            steps {
                gradlew 'classes testClasses'
            }
            
            // JavaDoc生成時に実行するとJavaDocの警告も含まれてしまうので
            // Javaコンパイル時の警告はコンパイル直後に収集
            post {
                always {
                    step([
                        $class: 'WarningsPublisher',
                        consoleParsers: [
                            [parserName: 'Java Compiler (javac)'],
                        ],
                        canComputeNew: false,
                        canResolveRelativesPaths: false,
                        usePreviousBuildAsReference: true
                    ])
                }
            }
        }
        
        stage('静的コード解析') {
            steps {
                parallel(
                    'Check' : {
                        gradlew 'check -x test'
                
                        dir(reportDir) {
                            step([
                                $class: 'CheckStylePublisher',
                                pattern: "checkstyle/*.xml"
                            ])
                            step([
                                $class: 'FindBugsPublisher',
                                pattern: "findbugs/*.xml"
                            ])
                            step([
                                $class: 'PmdPublisher',
                                pattern: "pmd/*.xml"
                            ])
                            step([
                                $class: 'DryPublisher',
                                pattern: "cpd/*.xml"
                            ])
                
                            archiveArtifacts "checkstyle/*.xml"
                            archiveArtifacts "findbugs/*.xml"
                            archiveArtifacts "pmd/*.xml"
                            archiveArtifacts "cpd/*.xml"
                        }
                    },
                    'ステップカウント': {
                        stepcounter outputFile: 'stepcount.xls', outputFormat: 'excel', settings: [
                            [key:'Java', filePattern: "${javaDir}/**/*.java"],
                            [key:'SQL', filePattern: "${resourcesDir}/**/*.sql"],
                            [key:'HTML', filePattern: "${resourcesDir}/**/*.html"],
                            [key:'JS', filePattern: "${resourcesDir}/**/*.js"],
                            [key:'CSS', filePattern: "${resourcesDir}/**/*.css"]
                        ]
                        archiveArtifacts "stepcount.xls"
                    },
                    'タスクスキャン': {
                        step([
                            $class: 'TasksPublisher',
                            pattern: './**',
                            high: 'System.out.System.err',
                            normal: 'TODO,FIXME,XXX',
                            ignoreCase: true,
                        ])
                    },
                    'JavaDoc': {
                        gradlew 'javadoc -x classes'
                        step([
                            $class: 'hudson.tasks.JavadocArchiver',
                            javadocDir: "${javadocDir}",
                            keepAll: true
                        ])
                    }
                )
            }
            
            // JavaDocの警告を収集
            post {
                always {
                    step([
                        $class: 'WarningsPublisher',
                        consoleParsers: [
                            [parserName: 'JavaDoc Tool']
                        ],
                        canComputeNew: false,
                        canResolveRelativesPaths: false,
                        usePreviousBuildAsReference: true
                    ])
                }
            }
        }
        

        stage('テスト') {
            steps {
                gradlew 'test jacocoTestReport -x classes -x testClasses'
                
                junit "${testReportDir}/*.xml"
                archiveArtifacts "${testReportDir}/*.xml"
                step([
                    $class: 'JacocoPublisher',
                    execPattern: "${jacocoReportDir}/*.exec",
                    exclusionPattern: '**/*Test.class,**/_*.class,**/TestHelper.class'
                ])
            }
        }
        
        stage('デプロイ') {
            // テスト失敗時はデプロイしない
            when {
                expression {currentBuild.currentResult == 'SUCCESS'}
            }
            
            steps {
                gradlew 'jar'
                archiveArtifacts "${libsDir}/${appName}-${appVersion}.jar"
                gradlew 'war'
                archiveArtifacts "${libsDir}/${appName}-${appVersion}.war"
                deploy warDir: libsDir, appName: appName, appVersion: appVersion
            }
        }
    }
    
    
    post {
        always {
            deleteDir()
        }
        changed {
            sendMail("${currentBuild.previousBuild.result} => ${currentBuild.currentResult}")
        }
        failure {
            sendMail(currentBuild.currentResult)
        }
        unstable {
            sendMail(currentBuild.currentResult)
        }
    }
}

def gradlew(command) {
    if(isUnix()) {
        sh "./gradlew ${command} --stacktrace"
    } else {
        bat "./gradlew.bat ${command} --stacktrace"
    }
}

// srgs.warDir warの格納ディレクトリ 
// args.appName アプリ名
// args.appVersion アプリのバージョン
def deploy(Map args) {
    def keyDir = '/var/lib/jenkins/.ssh/takumon.pem'
    def webServerAddress = 'ec2-54-148-2-4.us-west-2.compute.amazonaws.com'
    def webServerUser = 'ec2-user'
    def webServer = "${webServerUser}@${webServerAddress}"
    
    def srcWar = "${args.appName}-${args.appVersion}.war"
    def destWar = "${args.appName}.war"
    
    
    sh "sudo -S scp -i ${keyDir} ./${args.warDir}/${srcWar} ${webServer}:/home/ec2-user"
    sh "sudo -S ssh -i ${keyDir} ${webServer} \"sudo cp /home/ec2-user/${srcWar} /usr/share/tomcat8/webapps/${destWar}\""
}


def sendMail(result) {
    mail to: "inouetakumon@gmail.com",
        subject: "${env.JOB_NAME} #${env.BUILD_NUMBER} [${result}]",
        body: "Build URL: ${env.BUILD_URL}.\n\n"
}