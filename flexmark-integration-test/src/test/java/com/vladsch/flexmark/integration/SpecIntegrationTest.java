package com.vladsch.flexmark.integration;

import com.vladsch.flexmark.Extension;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.spec.SpecExample;
import com.vladsch.flexmark.test.SpecTestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tests that the spec examples still render the same with all extensions enabled.
 */
public class SpecIntegrationTest extends SpecTestCase {

    private static final List<Extension> EXTENSIONS = Arrays.asList(
            AutolinkExtension.create(),
            StrikethroughExtension.create(),
            TablesExtension.create(),
            YamlFrontMatterExtension.create());
    private static final Parser PARSER = Parser.builder().extensions(EXTENSIONS).build();
    // The spec says URL-escaping is optional, but the examples assume that it's enabled.
    private static final HtmlRenderer RENDERER = HtmlRenderer.builder().extensions(EXTENSIONS).percentEncodeUrls(true).build();
    private static final Map<String, String> OVERRIDDEN_EXAMPLES = getOverriddenExamples();

    public SpecIntegrationTest(SpecExample example) {
        super(example);
    }

    @Override
    public SpecExample example() {
        return null;
    }

    @Test
    @Override
    public void testHtmlRendering() {
        String expectedHtml = OVERRIDDEN_EXAMPLES.get(example.getSource());
        if (expectedHtml != null) {
            assertRendering(example.getSource(), expectedHtml);
        } else {
            super.testHtmlRendering();
        }
    }

    @Override
    public Parser parser() {
        return PARSER;
    }

    @Override
    public HtmlRenderer renderer() {
        return RENDERER;
    }

    private static Map<String, String> getOverriddenExamples() {
        Map<String, String> m = new HashMap<>();

        // Not a spec autolink because of space, but the resulting text contains a valid URL
        m.put("<http://foo.bar/baz bim>\n", "<p>&lt;<a href=\"http://foo.bar/baz\">http://foo.bar/baz</a> bim&gt;</p>\n");

        // Not a spec autolink, but the resulting text contains a valid email
        m.put("<foo\\+@bar.example.com>\n", "<p>&lt;<a href=\"mailto:foo+@bar.example.com\">foo+@bar.example.com</a>&gt;</p>\n");

        // Not a spec autolink because of unknown scheme, but autolink extension doesn't limit schemes
        m.put("<heck://bing.bong>\n", "<p>&lt;<a href=\"heck://bing.bong%3E\">heck://bing.bong&gt;</a></p>\n");

        // Not a spec autolink because of spaces, but autolink extension doesn't limit schemes
        m.put("< http://foo.bar >\n", "<p>&lt; <a href=\"http://foo.bar\">http://foo.bar</a> &gt;</p>\n");

        // Plain autolink
        m.put("http://example.com\n", "<p><a href=\"http://example.com\">http://example.com</a></p>\n");

        // Plain autolink
        m.put("foo@bar.example.com\n", "<p><a href=\"mailto:foo@bar.example.com\">foo@bar.example.com</a></p>\n");

        // YAML front matter block
        m.put("---\nFoo\n---\nBar\n---\nBaz\n", "<h2>Bar</h2>\n<p>Baz</p>\n");
        m.put("---\n---\n", "");

        return m;
    }
}
