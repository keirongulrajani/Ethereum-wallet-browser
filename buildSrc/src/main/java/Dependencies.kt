@file:Suppress("unused")

object Versions {

    const val gradle = "3.4.0"
    const val gradleVersionsPlugin = "0.20.0"
    const val jacoco = "0.8.3"
    const val kotlin = "1.3.30"
    const val buildTools = "28.0.3"

    // Android libraries
    const val appCompat = "1.1.0-alpha04"
    const val androidx = "1.1.0-alpha04"
    const val androidxKtx = "1.1.0-alpha05"
    const val design = "1.0.0-rc01"
    const val cardview = "1.0.0"
    const val multiDex = "2.0.0"
    const val constraintLayout = "1.1.3"

    // Third party libraries
    const val dagger = "2.22.1"
    const val retrofit = "2.5.0"
    const val okhttp = "3.12.1" // version 3.13.0 bumps min SDK to API 21, so don't update
    const val rxjava = "2.2.8"
    const val rxkotlin = "2.3.0"
    const val rxAndroid = "2.1.1"
    const val jetbrainsAnnotations = "17.0.0"

    // Unit Testing
    const val robolectric = "3.8"
    const val junit = "4.12"
    const val mockito = "2.24.5"
    const val mockitoKotlin = "1.6.0"

    // Image loading
    // Vector Animations
    const val lottie = "3.0.1"
    const val glide = "4.9.0"

    // Quality
    const val checkstyle = "8.18"
    const val ktlint = "0.30.0"
    const val findbugs = "3.0.2"

    // Arch components
    const val archComponents = "2.0.0-rc01"

    // Android tools
    const val androidTools = "26.3.1"

    // java apache commons
    const val commonsCollections4 = "4.3"
    const val commonsLang3 = "3.9"

    // ETH smart contract
    const val web3jCore = "4.2.0-android"
    const val web3jInfura = "4.2.0-android"
}

object ProjectDependencies {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val jacoco = "org.jacoco:org.jacoco.core:${Versions.jacoco}"
    const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersionsPlugin}"
}

object MainApplicationDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinKtx = "androidx.core:core-ktx:${Versions.androidxKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val multiDex = "androidx.multidex:multidex:${Versions.multiDex}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardview}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val constraintLayoutSolver = "androidx.constraintlayout:constraintlayout-solver:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.androidx}"
    const val design = "com.google.android.material:material:${Versions.design}"
    const val androidAnnotations = "androidx.annotation:annotation:${Versions.androidx}"
    const val supportV4 = "androidx.legacy:legacy-support-v4:${Versions.androidx}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitRxAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotations = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val rxjava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    const val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val archComponentLifeCycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.archComponents}"
    const val archComponentLifeCycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.archComponents}"
    const val lintapi = "com.android.tools.lint:lint-api:${Versions.androidTools}"
    const val lintchecks = "com.android.tools.lint:lint-checks:${Versions.androidTools}"
    const val jetbrainsAnnotations = "org.jetbrains:annotations:${Versions.jetbrainsAnnotations}"
    const val commonsCollections4 = "org.apache.commons:commons-collections4:${Versions.commonsCollections4}"
    const val commonsLang3 = "org.apache.commons:commons-lang3:${Versions.commonsLang3}"
    const val web3jCore = "org.web3j:core:${Versions.web3jCore}"
    const val web3jInfura = "org.web3j:infura:${Versions.web3jInfura}"
}

object UnitTestingDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val robolectricMultidex = "org.robolectric:shadows-multidex:${Versions.robolectric}"
    const val junit = "junit:junit:${Versions.junit}"
    const val mockito = "org.mockito:mockito-inline:${Versions.mockito}"
    const val archComponentCoreTesting = "androidx.arch.core:core-testing:${Versions.archComponents}"
}

object QualityDependencies {
    const val checkstyle = "com.puppycrawl.tools:checkstyle:${Versions.checkstyle}"
    const val ktlint = "com.github.shyiko:ktlint:${Versions.ktlint}"
    const val findbugs = "com.google.code.findbugs:jsr305:${Versions.findbugs}"
}