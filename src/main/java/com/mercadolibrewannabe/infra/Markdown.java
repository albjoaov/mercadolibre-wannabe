package com.mercadolibrewannabe.infra;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class Markdown {

	private static final Parser PARSER = Parser.builder().build();
	private static final HtmlRenderer RENDERER = HtmlRenderer.builder().build();

	private Markdown () { }

	public static String format (String rawText) {
		Node document = PARSER.parse(rawText);
		return RENDERER.render(document);
	}
}
