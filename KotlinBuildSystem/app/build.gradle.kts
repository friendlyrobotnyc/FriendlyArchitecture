plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    kotlin("kapt")
}


android {

    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
    }

    dependencies {

        testImplementation(testDependencies.junit)
        testImplementation(testDependencies.mockk)

        androidTestImplementation(testDependencies.junitKtx)
        androidTestImplementation(testDependencies.espresso)

        implementation(androidDependencies.kotlin)
        implementation(androidDependencies.appCompat)
        implementation(androidDependencies.ktxCore)
        implementation(androidDependencies.appCompat)
        implementation(androidDependencies.materialComponents)
        implementation(androidDependencies.constraintLayout)
        implementation(androidDependencies.recyclerView)
        implementation(libraryDependencies.moshi)

        //kapt
        kapt(libraryDependencies.moshiCodegen)
    }

}


//android {

//    defaultConfig {
//        applicationId "friendlyrobot.nyc.kotlinbuildsystem"
//        targetSdkVersion 28
//        versionCode 1
//        versionName "1.0"
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//    }
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//}
//
//dependencies {
//    implementation fileTree(dir: 'libs', include: ['*.jar'])
//    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
//    implementation 'androidx.appcompat:appcompat:1.0.2'
//    implementation 'androidx.core:core-ktx:1.0.2'
//    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
//    testImplementation 'junit:junit:4.12'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
//}
