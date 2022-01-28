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

	// Should have been loaded through 'java-application-conventions' as it part of `java-common-conventions`, 
	// but somehow it doesn't show up...
	//testImplementation("org.mockito:mockito-core:3.12.4")
	//testImplementation("org.mockito:mockito-junit-jupiter:3.12.4")
    //testImplementation("org.mockito:mockito-core:4.3.1")
}

tasks.withType<JavaCompile> {
    val compilerArgs = options.compilerArgs
//    compilerArgs.add("-Xdoclint:all,-missing")
//    compilerArgs.add("-Xlint:all")
    compilerArgs.add("-Aproject=com.github.verhagen/activity-tracker-notebook")
}


//tasks.withType<Jar> {
//	manifest.attributes["Main-Class"] = "com.github.verhagen.activitylogbook.app.App"
//	manifest()
//}

application {
//    // Define the main class for the application.
//    mainClass.set("com.github.verhagen.activitylogbook.app.App")
	mainClassName = "com.github.verhagen.activitylogbook.app.App"
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "com.github.verhagen.activitylogbook.app.App"
    }
}
