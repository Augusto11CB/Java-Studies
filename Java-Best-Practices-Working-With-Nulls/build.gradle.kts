buildscript {
    repositories {
        jcenter()
    }
}

plugins {
    java
    application
}

repositories {
    mavenCentral()
}


//sourceSets {
//    main {
//        java.srcDir("src/main/java")
//    }
//    test {
//        java.srcDir("src/test/java")
//    }
//}

application {
    mainClassName = "main.java.NullObjectApplication"
}

subprojects {

    apply(plugin = "java")
    apply(plugin = "application")

    group = "org.example"
    version = "1.0-SNAPSHOT"




    repositories {
        jcenter()
        mavenCentral()
    }

    dependencies {

        testCompile("junit", "junit", "4.12")
    }
}

//
//dependencies {
//    testCompile("junit", "junit", "4.12")
//}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}