pipeline {
    agent any
    environment {
        reportDir = 'build/reports'
        javaDir = 'src/main/java'
        resourcesDir = 'src/main/resources'
        testReportDir = 'build/test-results/test'
        jacocoReportDir = 'build/jacoco' 
        javadocDir = 'build/docs/javadoc'
    }
    
    stages {
        stage('事前準備') {
            steps {
                deleteDir()
                checkout scm
                script {
                    // permission deniedで怒られないために実行権限を付与する
                    if(isUnix()) {
                        sh 'chmod +x gradlew'
                    }
                    gradlew 'clean'
                }
            }
        }
        
        stage('コンパイル') {
            steps {
                parallel(
                    'ステップカウント': {
                        stepcounter outputFile: 'stepcount.xls', outputFormat: 'excel', settings: [
                            [key:'Java', filePattern: "${javaDir}/**/*.java"],
                            [key:'SQL', filePattern: "${resourcesDir}/**/*.sql"],
                            [key:'HTML', filePattern: "${resourcesDir}/**/*.html"],
                            [key:'JS', filePattern: "${resourcesDir}/**/*.js"],
                            [key:'CSS', filePattern: "${resourcesDir}/**/*.css"]
                        ]
                    },
                    'JavaDoc': {
                        gradlew 'javadoc -x classes'
                        step([
                            $class: 'hudson.tasks.JavadocArchiver',
                            javadocDir: "${javadocDir}",
                            keepAll: true
                        ])
                    },
                    'タスクスキャン': {
                        step([
                            $class: 'hudson.plugins.tasks.TasksPublisher',
                            pattern: './**',
                            high: 'System.out.System.err',
                            normal: 'TODO,FIXME,XXX',
                            ignoreCase: true,
                        ])
                    },
                    'コンパイル': {
                        gradlew 'classes testClasses'
                    }
                )
            }
        }
        

        stage('テストと静的コード解析') {
            steps {
                parallel(
                    '静的コード解析' : {
                        gradlew 'check -x test'
                
                        dir(reportDir) {
                            step([
                                $class: 'hudson.plugins.checkstyle.CheckStylePublisher',
                                pattern: "checkstyle/*.xml"
                            ])
                            step([
                                $class: 'hudson.plugins.findbugs.FindBugsPublisher',
                                pattern: "findbugs/*.xml"
                            ])
                            step([
                                $class: 'hudson.plugins.pmd.PmdPublisher',
                                pattern: "pmd/*.xml"
                            ])
                            step([
                                $class: 'hudson.plugins.dry.DryPublisher',
                                pattern: "cpd/*.xml"
                            ])
                
                            archiveArtifacts "checkstyle/*.xml"
                            archiveArtifacts "findbugs/*.xml"
                            archiveArtifacts "pmd/*.xml"
                            archiveArtifacts "cpd/*.xml"
                        }
                    },
                    'テスト' : {
                        gradlew 'test jacocoTestReport -x classes -x testClasses'
                
                        junit "${testReportDir}/*.xml"
                        archiveArtifacts "${testReportDir}/*.xml"
                        step([
                            $class: 'hudson.plugins.jacoco.JacocoPublisher',
                            execPattern: "${jacocoReportDir}/*.exec",
                            exclusionPattern: '**/*Test.class,**/_*.class,**/TestHelper.class'
                        ])
                    }
                )
            }
        }
    }
    
    
    post {
        always {
            deleteDir()
        }
        success {
            mail to: "inouetakumon@gmail.com", subject: 'SUCCESS', body: "passed."
        }
        failure {
            mail to: "inouetakumon@gmail.com", subject: 'FAILURE', body: "failed."
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
