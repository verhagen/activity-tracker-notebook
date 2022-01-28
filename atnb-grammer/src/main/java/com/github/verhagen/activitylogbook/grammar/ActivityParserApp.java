package com.github.verhagen.activitylogbook.grammar;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class ActivityParserApp {

	public static void main( String[] args) throws Exception {
		System.out.println("" + new ActivityParserApp().main(args[0]));
	}

	public String main(String input) throws Exception {
		CharStream charStream = CharStreams.fromString(input);

		ActivityLexer lexer = new ActivityLexer(charStream);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		ActivityParser parser = new ActivityParser(tokens);
		
		PlainActivityVisitor plainActivityVisitor = new  PlainActivityVisitor();
		
		
		ParseTree tree = parser.expr();    // begin parsing at rule 'expr'
		
		tree.accept(plainActivityVisitor);

		return tree.toStringTree(parser);  // print LISP-style tree
	}

}
