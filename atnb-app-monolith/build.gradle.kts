plugins {
    id("com.github.verhagen.activitylogbook.java-application-conventions")
}

dependencies {
    implementation(project(":atnb-core"))
    implementation(project(":atnb-domain"))
    implementation(project(":atnb-book"))
    implementation(project(":atnb-project"))
}

tasks.withType<JavaCompile> {
    val compilerArgs = options.compilerArgs
//    compilerArgs.add("-Xdoclint:all,-missing")
//    compilerArgs.add("-Xlint:all")
    compilerArgs.add("-Aproject=com.github.verhagen/activity-tracker-notebook")
}

application {
    // Define the main class for the application.
    mainClass.set("com.github.verhagen.activitylogbook.app.App")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "com.github.verhagen.activitylogbook.app.App"
    }
}
