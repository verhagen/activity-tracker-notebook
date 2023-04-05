
plugins {
    id("com.github.verhagen.activitylogbook.java-library-conventions")
    antlr
}

dependencies {
	antlr("org.antlr:antlr4:4.9.1")

	implementation("org.antlr:antlr4:4.9.1")
}

tasks.generateGrammarSource {
    maxHeapSize = "64m"
    arguments = arguments + listOf("-visitor", "-long-messages", "-package", "com.github.verhagen.activitylogbook.grammar")
}
/*
tasks.withType<AntlrTask> {
	source("src/main/antlr")
//    setArguments(new String[] {""})
    //source("build/generated-src/antlr4")

}
tasks.generateGrammarSource {
    maxHeapSize = "64m"
    arguments = arguments + listOf("-visitor", "-long-messages")
}
*/