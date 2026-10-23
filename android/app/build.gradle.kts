plugins {
    id("com.android.application")
}

val run = (System.getenv("GITHUB_RUN_NUMBER") ?: "1").toInt()

android {
    namespace = "com.parksunghyon.mallangpuzzle"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.parksunghyon.mallangpuzzle"
        minSdk = 24
        targetSdk = 36
        versionCode = run
        versionName = "1.0.$run"
    }
    signingConfigs {
        create("release") {
            val ks = System.getenv("KS_FILE")
            if (ks != null) {
                storeFile = file(ks)
                storePassword = System.getenv("KS_PASS")
                keyAlias = System.getenv("KEY_ALIAS")
                keyPassword = System.getenv("KEY_PASS")
            }
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            // 서명키가 있으면 서명, 없으면 서명 전 파일을 만들고 나중에 jarsigner로 서명
            if (System.getenv("KS_FILE") != null) signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
