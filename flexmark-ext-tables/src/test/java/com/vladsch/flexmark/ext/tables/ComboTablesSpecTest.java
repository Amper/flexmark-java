package com.vladsch.flexmark.ext.tables;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.internal.util.DataHolder;
import com.vladsch.flexmark.internal.util.MutableDataSet;
import com.vladsch.flexmark.node.Node;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.spec.SpecExample;
import com.vladsch.flexmark.spec.SpecReader;
import com.vladsch.flexmark.test.ComboSpecTestCase;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

public class ComboTablesSpecTest extends ComboSpecTestCase {
    static final String SPEC_RESOURCE = "/ext_tables_ast_spec.txt";
    private static final DataHolder OPTIONS = new MutableDataSet()
            .set(HtmlRenderer.INDENT_SIZE, 2)
            //.set(HtmlRenderer.PERCENT_ENCODE_URLS, true)
            .set(Parser.EXTENSIONS, Collections.singleton(TablesExtension.create()));

    private static final Map<String, DataHolder> optionsMap = new HashMap<>();
    static {
        optionsMap.put("gfm", new MutableDataSet()
                .set(TablesExtension.COLUMN_SPANS, false)
                .set(TablesExtension.APPEND_MISSING_COLUMNS, true)
                .set(TablesExtension.DISCARD_EXTRA_COLUMNS, true)
                .set(TablesExtension.HEADER_SEPARATOR_COLUMNS, true)
        );
    }

    static final Parser PARSER = Parser.builder(OPTIONS).build();
    // The spec says URL-escaping is optional, but the examples assume that it's enabled.
    static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();

    static DataHolder optionsSet(String optionSet) {
        if (optionSet == null) return null;

        return optionsMap.get(optionSet);
    }

    public ComboTablesSpecTest(SpecExample example) {
        super(example);
    }

    @Parameterized.Parameters(name = "{0}")
    public static List<Object[]> data() {
        List<SpecExample> examples = SpecReader.readExamples(SPEC_RESOURCE);
        List<Object[]> data = new ArrayList<>();

        // NULL example runs full spec test
        data.add(new Object[] { SpecExample.NULL });

        for (SpecExample example : examples) {
            data.add(new Object[] { example });
        }
        return data;
    }

    @Override
    protected DataHolder options(String optionSet) {
        return optionsSet(optionSet);
    }

    @Override
    protected String getSpecResourceName() {
        return SPEC_RESOURCE;
    }

    @Override
    protected Parser parser() {
        return PARSER;
    }

    @Override
    protected HtmlRenderer renderer() {
        return RENDERER;
    }

    @Test
    public void testWrap() throws Exception {
        if (!example.isFullSpecExample()) return;

        final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();
        final Parser PARSER = Parser.builder(OPTIONS).build();

        InputStream inputStream = SpecReader.class.getResourceAsStream("/wrap.md");
        if (inputStream == null) {
            throw new IllegalStateException("Could not load /wrap.md classpath resource");
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {
            Node node = PARSER.parseReader(reader);
            String html = RENDERER.render(node);
        }
    }

    @Test
    public void testTable() throws Exception {
        if (!example.isFullSpecExample()) return;

        final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();
        final Parser PARSER = Parser.builder(OPTIONS).build();

        InputStream inputStream = SpecReader.class.getResourceAsStream("/table.md");
        if (inputStream == null) {
            throw new IllegalStateException("Could not load /table.md classpath resource");
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName("UTF-8")))) {
            Node node = PARSER.parseReader(reader);
            String html = RENDERER.render(node);
        }
    }
}
