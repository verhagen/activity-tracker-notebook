
plugins {
    id("com.github.verhagen.activitylogbook.java-library-conventions")
    antlr
}

dependencies {
	antlr("org.antlr:antlr4:4.9.1")

	implementation("org.antlr:antlr4:4.9.1")
}

tasks.withType<AntlrTask> {
	source("src/main/antlr")
    //source("build/generated-src/antlr4")
}
tasks.generateGrammarSource {
    maxHeapSize = "64m"
    arguments = arguments + listOf("-visitor", "-long-messages")
}
