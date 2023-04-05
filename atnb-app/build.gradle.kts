plugins {
    id("com.github.verhagen.activitylogbook.java-application-conventions")
}

dependencies {
    implementation(project(":atnb-core"))
    implementation(project(":atnb-domain"))
    implementation(project(":atnb-book"))
    implementation(project(":atnb-project"))

    implementation("info.picocli:picocli:4.6.1")
    annotationProcessor("info.picocli:picocli-codegen:4.6.1")
}

tasks.withType<JavaCompile> {
    val compilerArgs = options.compilerArgs
//    compilerArgs.add("-Xdoclint:all,-missing")
//    compilerArgs.add("-Xlint:all")
    compilerArgs.add("-Aproject=com.github.verhagen/activity-logbook")
}


//tasks.withType<Jar> {
//	manifest.attributes["Main-Class"] = "com.github.verhagen.activitylogbook.app.App"
//	manifest()
//}

application {
    // Define the main class for the application.
    mainClass.set("com.github.verhagen.activitylogbook.app.App")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "com.github.verhagen.activitylogbook.app.App"
    }
}
