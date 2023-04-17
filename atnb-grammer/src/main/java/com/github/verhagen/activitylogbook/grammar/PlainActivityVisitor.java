package com.github.verhagen.atnb.grammar;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.verhagen.atnb.grammar.ActivityParser.ActivityExpressionContext;
import com.github.verhagen.atnb.grammar.ActivityParser.ExprContext;
import com.github.verhagen.atnb.grammar.ActivityParser.WhatExpressionContext;

public class PlainActivityVisitor extends ActivityBaseVisitor<String> {
	private Logger logger = LoggerFactory.getLogger(PlainActivityVisitor.class);
	private List<String> content = new ArrayList<>();


	@Override
	public String visitExpr(ExprContext ctx) {
		// TODO Auto-generated method stub
		content.add(ctx.getText());
		logger.info("visit expr " + ctx);
		return "expr " + super.visitExpr(ctx);
	}

	@Override
	public String visitActivityExpression(ActivityExpressionContext ctx) {
		// TODO Auto-generated method stub
		content.add(ctx.getText());
		logger.info("visit Activity Expression " + ctx);
		return super.visitActivityExpression(ctx);
	}

	@Override
	public String visitWhatExpression(WhatExpressionContext ctx) {
		// TODO Auto-generated method stub
		content.add(ctx.getText());
		logger.info("visit What Expression " + ctx);
		return super.visitWhatExpression(ctx);
	}

	@Override
	public String toString() {
		StringBuilder bldr = new StringBuilder();
		for (String text : content) {
			bldr.append(text).append("\n");
		}
		return bldr.toString();
	}
}
