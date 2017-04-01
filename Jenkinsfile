node {
    stage('チェックアウト') {
        checkout([
            $class: 'GitSCM', 
            branches: [[name: '*/master']], 
            userRemoteConfigs: [[url: 'https://github.com/Takumon/JapaneseAddressSeacher.git']]
        ])
    }
    
    stage('クリーン') {
        // permission deniedで怒られないために実行権限を付与する
        if(isUnix()) {
            sh 'chmod +x gradlew'
        }
        gradle('clean')
    }
    
    parallel(
        'ステップカウント': {
            stage('ステップカウント') {
                stepcounter outputFile: 'stepcount.xls', outputFormal: 'excel', settings: [
                  [key:'Java', filePattern: '**.java'],
                  [key:'SQL', filePattern: '**.sql'],
                  [key:'HTML', filePattern: '**.html'],
                  [key:'JS', filePattern: '**.js'],
                  [key:'CSS', filePattern: '**.css']
                ]
            }
        },
        'JavaDoc': {
            stage('JavaDoc') {
                gradle('javadoc -x classes')
                step([
                    $class: 'hudson.tasks.JavadocArchiver',
                    javadocDir: './build/docs/javadoc',
                    keepAll: true
                    
                ])
            }
        },
        'タスクスキャン': {
            stage('タスクスキャン') {
                step([
                    $class: 'hudson.plugins.tasks.TasksPublisher',
                    pattern: './**',
                    high: 'System.out.System.err',
                    normal: 'TODO,FIXME,XXX',
                    ignoreCase: true,
                ])
            }
        },
        'コンパイル': {
            stage('コンパイル') {
                gradle('classes testClasses')
            }
        }
    )


    parallel(
        '静的コード解析' : {
            stage('静的コード解析') {
                gradle('check -x test')
                step([
                    $class: 'hudson.plugins.checkstyle.CheckStylePublisher',
                    pattern: '**/checkstyle/*.xml'
                ])
                step([
                    $class: 'hudson.plugins.findbugs.FindBugsPublisher',
                    pattern: '**/findbugs/*.xml'
                ])
                step([
                    $class: 'hudson.plugins.pmd.PmdPublisher',
                    pattern: '**/pmd/*.xml'
                ])
                step([
                    $class: 'hudson.plugins.dry.DryPublisher',
                    pattern: '**/cpd/*.xml'
                ])
                
                archiveArtifacts "**/checkstyle/*.xml"
                archiveArtifacts "**/findbugs/*.xml"
                archiveArtifacts "**/pmd/*.xml"
                archiveArtifacts "**/cpd/*.xml"
            }
        },
        'テスト' : {
            stage('テスト') {
                gradle('test jacocoTestReport -x classes -x testClasses')
                junit '**/test-results/test/*.xml'
                archiveArtifacts '**/test-results/test/*.xml'
                
                step([
                    $class: 'hudson.plugins.jacoco.JacocoPublisher',
                    execPattern: '**/jacoco/*.exec'
                ])
                
            }
        }
    )
}

def gradle(command) {
    if(isUnix()) {
        sh "./gradlew ${command} --stacktrace"
    } else {
        bat "./gradlew.bat  ${command} --stacktrace"
    }
}
