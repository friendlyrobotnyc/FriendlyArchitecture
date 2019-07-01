object Versions {

    val compileSdk = 28
    val targetSdk = 28
    val minSdk = 21
    val buildTools = "28.0.3"
    val coroutines = "1.2.2"
    val gradlePlugin = "3.6.0-alpha04"
    val kotlin = "1.3.40"
    val ktxCore = "1.0.2"

    //android
    val appCompat = "1.0.2"
    val constraintLayout = "1.1.3"
    val googlePlay = "4.2.0"
    val googleServicesPlugin = "4.2.0"
    val materialComponents = "1.1.0-alpha07"
    val navigation = "2.0.0"
    val recyclerView = "1.0.0"
    val viewModelKtx = "2.2.0-alpha01"
    val viewpager = "1.0.0-alpha04"

    //lib
    val apollo = "1.0.0"
    val apolloCoroutines = "1.0.0-alpha5"
    val auth0 = "1.16.0"
    val auth0Lock = "2.14.1"
    val autoValue = "1.6"
    val crashlytics = "2.9.8"
    val dagger = "2.22.1"
    val epoxy = "3.5.1"
    val fabricPlugin = "1.29.0"
    val firebaseCore = "17.0.0"
    val firebaseMsg = "19.0.1"
    val firebasePerfPlugin = "1.2.0"
    val firebasePerf = "18.0.1"
    val firebaseConfig = "18.0.0"
    val glide = "4.9.0"
    val iterable = "3.1.1"
    val koin = "2.0.1"
    val moshi = "1.8.0"
    val mvRx = "1.0.2"
    val nytimesstore = "3.1.1"
    val okhttp = "3.14.2"
    val publisher = "2.1.0"
    val retrofit = "2.6.0"
    val rxAndroid = "2.1.1"
    val rxJava = "2.2.10"
    val snowplow = "1.1.0"
    val timber = "4.7.1"

    //test
    val assertj = "3.11.1"
    val espresso = "3.1.0"
    val mockk = "1.9"
    val robolectric = "4.2"
    val stetho = "1.5.0"
    val testCoroutines = "1.2.0"
    val truth = "1.0.0"
    val junit = "4.12"
    val junitKtx = "1.1.1"
    val testRunner = "1.1.0"

    //static
    val findBugs = "3.0.2"
    val jacoco = "0.1.4"
    val jacocoTools = "0.8.3"
    val ktLint = "0.29.0"

    //annotations
    val javaXAnnotations = "1.0"
    val jetbrainsAnnotations = "13.0"
}

object gradleDependencies {
    val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val googleServicesPlugin = "com.google.gms:google-services:${Versions.googleServicesPlugin}"
    val fabricPlugin = "io.fabric.tools:gradle:${Versions.fabricPlugin}"
    val ktLint = "com.github.shyiko:ktlint:${Versions.ktLint}"
    val jacoco = "com.dicedmelon.gradle:jacoco-android:${Versions.jacoco}"
    val publisher = "com.github.triplet.gradle:play-publisher:${Versions.publisher}"
    val perfPlugin = "com.google.firebase:perf-plugin:${Versions.firebasePerfPlugin}"
    val apollo = "com.apollographql.apollo:apollo-gradle-plugin:${Versions.apollo}"
}

object testDependencies {
    val testRunner = "androidx.test:runner:${Versions.testRunner}"
    val junit = "junit:junit:${Versions.junit}"
    val junitKtx = "androidx.test.ext:junit:${Versions.junitKtx}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val espressoIntents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    val mockk = "io.mockk:mockk:${Versions.mockk}"
    val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    val truth = "androidx.test.ext:truth:${Versions.truth}"
    val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.testCoroutines}"
    val findBugs = "com.google.code.findbugs:jsr305:${Versions.findBugs}"
    val orchestrator = "androidx.test:orchestrator:${Versions.testRunner}"
    val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
    val assertj = "org.assertj:assertj-core:${Versions.assertj}"
}

object androidDependencies {
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val ktxCore = "androidx.core:core-ktx:${Versions.ktxCore}"
    val materialComponents = "com.google.android.material:material:${Versions.materialComponents}"
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelKtx}"
    val viewModel = "androidx.lifecycle:lifecycle-extensions:${Versions.viewModelKtx}"
    val viewpager = "androidx.viewpager2:viewpager2:${Versions.viewpager}"
}

object libraryDependencies {
    val apolloRuntime = "com.apollographql.apollo:apollo-runtime:${Versions.apollo}"
    val apolloAndroid = "com.apollographql.apollo:apollo-android-support:${Versions.apollo}"
    val apolloCoroutines =
        "com.apollographql.apollo:apollo-coroutine-support:${Versions.apolloCoroutines}"
    val apolloRx = "com.apollographql.apollo:apollo-rx2-support:${Versions.apollo}"
    val auth0 = "com.auth0.android:auth0:${Versions.auth0}"
    val auth0Lock = "com.auth0.android:lock:${Versions.auth0Lock}"
    val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val epoxy = "com.airbnb.android:epoxy:${Versions.epoxy}"
    val epoxyProcessor = "com.airbnb.android:epoxy-processor:${Versions.epoxy}"
    val firebaseCore = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
    val firebaseMsg = "com.google.firebase:firebase-messaging:${Versions.firebaseMsg}"
    val firebasePerf = "com.google.firebase:firebase-perf:${Versions.firebasePerf}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val iterable = "com.iterable:iterableapi:${Versions.iterable}"
    val koin = "org.koin:koin-android:${Versions.koin}"
    val koinScope = "org.koin:koin-android-scope:${Versions.koin}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    val moshiRetrofit = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val mvRx = "com.airbnb.android:mvrx:${Versions.mvRx}"
    val nytimesstore = "com.nytimes.android:store3:${Versions.nytimesstore}"
    val nytimesstorecache = "com.nytimes.android:cache3:${Versions.nytimesstore}"
    val nytimesstorekotlin = "com.nytimes.android:store3:${Versions.nytimesstore}"
    val nytimesmiddle = "com.nytimes.android:middleware3:${Versions.nytimesstore}"
    val nytimesfilesystem = "com.nytimes.android:filesystem3:${Versions.nytimesstore}"
    val nytimesmiddlemoshi = "com.nytimes.android:middleware-moshi3:${Versions.nytimesstore}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val snowplow = "com.snowplowanalytics:snowplow-android-tracker:${Versions.snowplow}@aar"
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object annotationDependencies {
    val autoValueAnnotations = "com.google.auto.value:auto-value-annotations:${Versions.autoValue}"
    val dagger = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val glideAnnotations = "com.github.bumptech.glide:compiler:${Versions.glide}"
    val jetbrainsAnnotations = "org.jetbrains:annotations:${Versions.jetbrainsAnnotations}"
    val javaXAnnotations = "javax.annotation:jsr250-api:${Versions.javaXAnnotations}"
}