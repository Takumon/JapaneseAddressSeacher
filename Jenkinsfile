node {
    def workspaceTempDir = "${WORKSPACE}@script"
    
    stage('事前処理') {
        // permission deniedで怒られないために実行権限を付与する
        if(isUnix()) {
            sh 'chmod +x gradlew'
        }
        gradle('clean')
    }

    stage('コンパイル') {
        gradle('classes testClasses')
    }
    
    parallel (
        '静的コード解析' : {
            stage('静的コード解析') {
                gradle('check -x test')
            }
        },
        'テスト' : {
            stage('テスト') {
                gradle('test jacocoTestReport -x classes -x testClasses')
                junit '**/test-result*.xml'
                archiveArtifacts artifacts: '**/test-result*.xml'
                
                step([
                    $class: 'hudson.plugins.jacoco.JacocoPublisher',
                    execPattern: 'build/gradle/jacoco/*.exec',
                    classPattern: 'build/gradle/classes/main',
                    sourcePattern: 'src/main/java'
                ])
            }
        }
    )

}

def gradle(command) {
    if(isUnix()) {
        sh './gradlew ${command} --stacktrace'
    } else {
        bat './gradlew.bat  ${command} --stacktrace'
    }
}


