package com.vladsch.flexmark.util.sequence;

import com.vladsch.flexmark.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BasedTrackedSequenceImplTest {
    // TODO: need complete tests here

    @Test
    public void test_indexOf() throws Exception {
        final String s1 = "01234567890123456789";
        BasedSequence s = BasedSequence.of(s1, 0, ((CharSequence) s1).length());
        int iMax = s.length();

        assertEquals(s1.indexOf(' '), s.indexOf(' '));

        for (int i = 0; i < iMax; i++) {
            final char c = (char) ('0' + (i % 10));
            assertEquals("indexOf('" + c + "')", s1.indexOf(c), s.indexOf(c));
            for (int j = i; j < iMax; j++) {
                assertEquals("indexOf('" + c + "', " + j + ")", s1.indexOf(c, j), s.indexOf(c, j));
                for (int k = iMax; k-- > j; ) {
                    assertEquals("indexOf('" + c + "', " + j + ", " + k + ")", s1.substring(0, k).indexOf(c, j), s.indexOf(c, j, k));
                }
            }
        }
    }

    @Test
    public void test_lastIndexOf() throws Exception {
        final String s1 = "01234567890123456789";
        BasedSequence s = BasedSequence.of(s1, 0, ((CharSequence) s1).length());
        int iMax = s.length();

        assertEquals(s1.lastIndexOf(' '), s.lastIndexOf(' '));

        for (int i = 0; i < iMax; i++) {
            final char c = (char) ('0' + (i % 10));
            assertEquals("lastIndexOf('" + c + "')", s1.lastIndexOf(c), s.lastIndexOf(c));
            for (int j = i; j < iMax; j++) {
                assertEquals("lastIndexOf('" + c + "', " + j + ")", s1.lastIndexOf(c, j), s.lastIndexOf(c, j));
                for (int k = iMax; k-- > j; ) {
                    final int lastIndexOf = s1.lastIndexOf(c, k);
                    assertEquals("lastIndexOf('" + c + "', " + j + ", " + k + ")", lastIndexOf < j ? -1 : lastIndexOf, s.lastIndexOf(c, j, k));
                }
            }
        }
    }

    @Test
    public void test_endSequence() {
        assertEquals("", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(0).toString());
        assertEquals("9", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(1).toString());
        assertEquals("89", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(2).toString());
        assertEquals("789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(3).toString());
        assertEquals("6789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(4).toString());
        assertEquals("56789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(5).toString());
        assertEquals("456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(6).toString());
        assertEquals("3456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(7).toString());
        assertEquals("23456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(8).toString());
        assertEquals("123456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(9).toString());
        assertEquals("0123456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(10).toString());
    }

    @Test
    public void test_endSequence2() {
        assertEquals("", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(0, 2).toString());
        assertEquals("", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(1, 2).toString());
        assertEquals("", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(2, 2).toString());
        assertEquals("7", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(3, 2).toString());
        assertEquals("67", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(4, 2).toString());
        assertEquals("567", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(5, 2).toString());
        assertEquals("4567", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(6, 2).toString());
        assertEquals("34567", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(7, 2).toString());
        assertEquals("234567", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(8, 2).toString());
        assertEquals("1234567", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(9, 2).toString());
        assertEquals("01234567", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).endSequence(10, 2).toString());
    }

    @Test
    public void test_midSequence() {
        assertEquals("0123456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(0).toString());
        assertEquals("123456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(1).toString());
        assertEquals("23456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(2).toString());
        assertEquals("3456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(3).toString());
        assertEquals("456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(4).toString());
        assertEquals("56789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(5).toString());
        assertEquals("6789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(6).toString());
        assertEquals("789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(7).toString());
        assertEquals("89", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(8).toString());
        assertEquals("9", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(9).toString());
        assertEquals("", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(10).toString());
        assertEquals("9", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(-1).toString());
        assertEquals("89", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(-2).toString());
        assertEquals("789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(-3).toString());
        assertEquals("6789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(-4).toString());
        assertEquals("56789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(-5).toString());
        assertEquals("456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(-6).toString());
        assertEquals("3456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(-7).toString());
        assertEquals("23456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(-8).toString());
        assertEquals("123456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(-9).toString());
        assertEquals("0123456789", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(-10).toString());
    }

    @Test
    public void test_midSequence2() {
        assertEquals("12345678", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(1, -1).toString());
        assertEquals("234567", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(2, -2).toString());
        assertEquals("3456", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(3, -3).toString());
        assertEquals("45", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(4, -4).toString());
        assertEquals("", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(5, -5).toString());
        assertEquals("", BasedSequence.of("0123456789", 0, ((CharSequence) "0123456789").length()).midSequence(6, -6).toString());
    }

    @Test
    public void test_countLeading() throws Exception {
        assertEquals(0, BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).countLeading(""));
        assertEquals("       ".length(), BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).countLeading(" "));
        assertEquals("    ".length(), BasedSequence.of("    abcdef   ", 0, ((CharSequence) "    abcdef   ").length()).countLeading(" "));
        assertEquals("    ".length(), BasedSequence.of("    abcdef ", 0, ((CharSequence) "    abcdef ").length()).countLeading(" "));
        assertEquals("    ".length(), BasedSequence.of("    abcdef", 0, ((CharSequence) "    abcdef").length()).countLeading(" "));
        assertEquals(" ".length(), BasedSequence.of(" abcdef   ", 0, ((CharSequence) " abcdef   ").length()).countLeading(" "));
        assertEquals(" ".length(), BasedSequence.of(" abcdef ", 0, ((CharSequence) " abcdef ").length()).countLeading(" "));
        assertEquals(" ".length(), BasedSequence.of(" abcdef", 0, ((CharSequence) " abcdef").length()).countLeading(" "));
        assertEquals("".length(), BasedSequence.of("abcdef   ", 0, ((CharSequence) "abcdef   ").length()).countLeading(" "));
        assertEquals("".length(), BasedSequence.of("abcdef ", 0, ((CharSequence) "abcdef ").length()).countLeading(" "));
        assertEquals("".length(), BasedSequence.of("abcdef", 0, ((CharSequence) "abcdef").length()).countLeading(" "));
    }

    @Test
    public void test_countTrailing() throws Exception {
        assertEquals(0, BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).countTrailing(""));
        assertEquals("       ".length(), BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).countTrailing(" "));
        assertEquals("   ".length(), BasedSequence.of("    abcdef   ", 0, ((CharSequence) "    abcdef   ").length()).countTrailing(" "));
        assertEquals(" ".length(), BasedSequence.of("    abcdef ", 0, ((CharSequence) "    abcdef ").length()).countTrailing(" "));
        assertEquals("".length(), BasedSequence.of("    abcdef", 0, ((CharSequence) "    abcdef").length()).countTrailing(" "));
        assertEquals("   ".length(), BasedSequence.of(" abcdef   ", 0, ((CharSequence) " abcdef   ").length()).countTrailing(" "));
        assertEquals(" ".length(), BasedSequence.of(" abcdef ", 0, ((CharSequence) " abcdef ").length()).countTrailing(" "));
        assertEquals("".length(), BasedSequence.of(" abcdef", 0, ((CharSequence) " abcdef").length()).countTrailing(" "));
        assertEquals("   ".length(), BasedSequence.of("abcdef   ", 0, ((CharSequence) "abcdef   ").length()).countTrailing(" "));
        assertEquals(" ".length(), BasedSequence.of("abcdef ", 0, ((CharSequence) "abcdef ").length()).countTrailing(" "));
        assertEquals("".length(), BasedSequence.of("abcdef", 0, ((CharSequence) "abcdef").length()).countTrailing(" "));
    }

    @Test
    public void test_trimRange() throws Exception {
        assertEquals(Range.of(7, 7), BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).trimRange());
        assertEquals(Range.of(4, 10), BasedSequence.of("    abcdef   ", 0, ((CharSequence) "    abcdef   ").length()).trimRange());
        assertEquals(Range.of(4, 10), BasedSequence.of("    abcdef ", 0, ((CharSequence) "    abcdef ").length()).trimRange());
        assertEquals(Range.of(4, 10), BasedSequence.of("    abcdef", 0, ((CharSequence) "    abcdef").length()).trimRange());
        assertEquals(Range.of(1, 7), BasedSequence.of(" abcdef   ", 0, ((CharSequence) " abcdef   ").length()).trimRange());
        assertEquals(Range.of(1, 7), BasedSequence.of(" abcdef ", 0, ((CharSequence) " abcdef ").length()).trimRange());
        assertEquals(Range.of(1, 7), BasedSequence.of(" abcdef", 0, ((CharSequence) " abcdef").length()).trimRange());
        assertEquals(Range.of(0, 6), BasedSequence.of("abcdef   ", 0, ((CharSequence) "abcdef   ").length()).trimRange());
        assertEquals(Range.of(0, 6), BasedSequence.of("abcdef ", 0, ((CharSequence) "abcdef ").length()).trimRange());
        assertSame(Range.NULL, BasedSequence.of("abcdef", 0, ((CharSequence) "abcdef").length()).trimRange());
    }

    @Test
    public void test_trimRangeKeep1() throws Exception {
        assertEquals(Range.of(6, 7), BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).trimRange(1));
        assertEquals(Range.of(3, 11), BasedSequence.of("    abcdef   ", 0, ((CharSequence) "    abcdef   ").length()).trimRange(1));
        assertEquals(Range.of(3, 11), BasedSequence.of("    abcdef ", 0, ((CharSequence) "    abcdef ").length()).trimRange(1));
        assertEquals(Range.of(3, 10), BasedSequence.of("    abcdef", 0, ((CharSequence) "    abcdef").length()).trimRange(1));
        assertEquals(Range.of(0, 8), BasedSequence.of(" abcdef   ", 0, ((CharSequence) " abcdef   ").length()).trimRange(1));
        assertSame(Range.NULL, BasedSequence.of(" abcdef ", 0, ((CharSequence) " abcdef ").length()).trimRange(1));
        assertSame(Range.NULL, BasedSequence.of(" abcdef", 0, ((CharSequence) " abcdef").length()).trimRange(1));
        assertEquals(Range.of(0, 7), BasedSequence.of("abcdef   ", 0, ((CharSequence) "abcdef   ").length()).trimRange(1));
        assertSame(Range.NULL, BasedSequence.of("abcdef ", 0, ((CharSequence) "abcdef ").length()).trimRange(1));
        assertSame(Range.NULL, BasedSequence.of("abcdef", 0, ((CharSequence) "abcdef").length()).trimRange(1));
    }

    @Test
    public void test_trim() throws Exception {
        assertEquals("", BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).trim().toString());
        assertEquals("abcdef", BasedSequence.of("    abcdef   ", 0, ((CharSequence) "    abcdef   ").length()).trim().toString());
        assertEquals("abcdef", BasedSequence.of("    abcdef ", 0, ((CharSequence) "    abcdef ").length()).trim().toString());
        assertEquals("abcdef", BasedSequence.of("    abcdef", 0, ((CharSequence) "    abcdef").length()).trim().toString());
        assertEquals("abcdef", BasedSequence.of(" abcdef   ", 0, ((CharSequence) " abcdef   ").length()).trim().toString());
        assertEquals("abcdef", BasedSequence.of(" abcdef ", 0, ((CharSequence) " abcdef ").length()).trim().toString());
        assertEquals("abcdef", BasedSequence.of(" abcdef", 0, ((CharSequence) " abcdef").length()).trim().toString());
        assertEquals("abcdef", BasedSequence.of("abcdef   ", 0, ((CharSequence) "abcdef   ").length()).trim().toString());
        assertEquals("abcdef", BasedSequence.of("abcdef ", 0, ((CharSequence) "abcdef ").length()).trim().toString());
        assertEquals("abcdef", BasedSequence.of("abcdef", 0, ((CharSequence) "abcdef").length()).trim().toString());
    }

    @Test
    public void test_trimKeep1() throws Exception {
        assertEquals("", BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).trim().toString());
        assertEquals(" abcdef ", BasedSequence.of("    abcdef   ", 0, ((CharSequence) "    abcdef   ").length()).trim(1).toString());
        assertEquals(" abcdef ", BasedSequence.of("    abcdef ", 0, ((CharSequence) "    abcdef ").length()).trim(1).toString());
        assertEquals(" abcdef", BasedSequence.of("    abcdef", 0, ((CharSequence) "    abcdef").length()).trim(1).toString());
        assertEquals(" abcdef ", BasedSequence.of(" abcdef   ", 0, ((CharSequence) " abcdef   ").length()).trim(1).toString());
        assertEquals(" abcdef ", BasedSequence.of(" abcdef ", 0, ((CharSequence) " abcdef ").length()).trim(1).toString());
        assertEquals(" abcdef", BasedSequence.of(" abcdef", 0, ((CharSequence) " abcdef").length()).trim(1).toString());
        assertEquals("abcdef ", BasedSequence.of("abcdef   ", 0, ((CharSequence) "abcdef   ").length()).trim(1).toString());
        assertEquals("abcdef ", BasedSequence.of("abcdef ", 0, ((CharSequence) "abcdef ").length()).trim(1).toString());
        assertEquals("abcdef", BasedSequence.of("abcdef", 0, ((CharSequence) "abcdef").length()).trim(1).toString());
    }

    @Test
    public void test_trimStart() throws Exception {
        assertEquals("", BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).trimStart().toString());
        assertEquals("abcdef   ", BasedSequence.of("    abcdef   ", 0, ((CharSequence) "    abcdef   ").length()).trimStart().toString());
        assertEquals("abcdef ", BasedSequence.of("    abcdef ", 0, ((CharSequence) "    abcdef ").length()).trimStart().toString());
        assertEquals("abcdef", BasedSequence.of("    abcdef", 0, ((CharSequence) "    abcdef").length()).trimStart().toString());
        assertEquals("abcdef   ", BasedSequence.of(" abcdef   ", 0, ((CharSequence) " abcdef   ").length()).trimStart().toString());
        assertEquals("abcdef ", BasedSequence.of(" abcdef ", 0, ((CharSequence) " abcdef ").length()).trimStart().toString());
        assertEquals("abcdef", BasedSequence.of(" abcdef", 0, ((CharSequence) " abcdef").length()).trimStart().toString());
        assertEquals("abcdef   ", BasedSequence.of("abcdef   ", 0, ((CharSequence) "abcdef   ").length()).trimStart().toString());
        assertEquals("abcdef ", BasedSequence.of("abcdef ", 0, ((CharSequence) "abcdef ").length()).trimStart().toString());
        assertEquals("abcdef", BasedSequence.of("abcdef", 0, ((CharSequence) "abcdef").length()).trimStart().toString());
    }

    @Test
    public void test_trimmedStart() throws Exception {
        assertEquals("       ", BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).trimmedStart().toString());
        assertEquals("    ", BasedSequence.of("    abcdef   ", 0, ((CharSequence) "    abcdef   ").length()).trimmedStart().toString());
        assertEquals("    ", BasedSequence.of("    abcdef ", 0, ((CharSequence) "    abcdef ").length()).trimmedStart().toString());
        assertEquals("    ", BasedSequence.of("    abcdef", 0, ((CharSequence) "    abcdef").length()).trimmedStart().toString());
        assertEquals(" ", BasedSequence.of(" abcdef   ", 0, ((CharSequence) " abcdef   ").length()).trimmedStart().toString());
        assertEquals(" ", BasedSequence.of(" abcdef ", 0, ((CharSequence) " abcdef ").length()).trimmedStart().toString());
        assertEquals(" ", BasedSequence.of(" abcdef", 0, ((CharSequence) " abcdef").length()).trimmedStart().toString());
        assertEquals("", BasedSequence.of("abcdef   ", 0, ((CharSequence) "abcdef   ").length()).trimmedStart().toString());
        assertEquals("", BasedSequence.of("abcdef ", 0, ((CharSequence) "abcdef ").length()).trimmedStart().toString());
        assertEquals("", BasedSequence.of("abcdef", 0, ((CharSequence) "abcdef").length()).trimmedStart().toString());
    }

    @Test
    public void test_trimStartKeep1() throws Exception {
        assertEquals(" ", BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).trimStart(1).toString());
        assertEquals(" abcdef   ", BasedSequence.of("    abcdef   ", 0, ((CharSequence) "    abcdef   ").length()).trimStart(1).toString());
        assertEquals(" abcdef ", BasedSequence.of("    abcdef ", 0, ((CharSequence) "    abcdef ").length()).trimStart(1).toString());
        assertEquals(" abcdef", BasedSequence.of("    abcdef", 0, ((CharSequence) "    abcdef").length()).trimStart(1).toString());
        assertEquals(" abcdef   ", BasedSequence.of(" abcdef   ", 0, ((CharSequence) " abcdef   ").length()).trimStart(1).toString());
        assertEquals(" abcdef ", BasedSequence.of(" abcdef ", 0, ((CharSequence) " abcdef ").length()).trimStart(1).toString());
        assertEquals(" abcdef", BasedSequence.of(" abcdef", 0, ((CharSequence) " abcdef").length()).trimStart(1).toString());
        assertEquals("abcdef   ", BasedSequence.of("abcdef   ", 0, ((CharSequence) "abcdef   ").length()).trimStart(1).toString());
        assertEquals("abcdef ", BasedSequence.of("abcdef ", 0, ((CharSequence) "abcdef ").length()).trimStart(1).toString());
        assertEquals("abcdef", BasedSequence.of("abcdef", 0, ((CharSequence) "abcdef").length()).trimStart(1).toString());
    }

    @Test
    public void test_trimEnd() throws Exception {
        assertEquals("", BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).trimEnd().toString());
        assertEquals("    abcdef", BasedSequence.of("    abcdef   ", 0, ((CharSequence) "    abcdef   ").length()).trimEnd().toString());
        assertEquals("    abcdef", BasedSequence.of("    abcdef ", 0, ((CharSequence) "    abcdef ").length()).trimEnd().toString());
        assertEquals("    abcdef", BasedSequence.of("    abcdef", 0, ((CharSequence) "    abcdef").length()).trimEnd().toString());
        assertEquals(" abcdef", BasedSequence.of(" abcdef   ", 0, ((CharSequence) " abcdef   ").length()).trimEnd().toString());
        assertEquals(" abcdef", BasedSequence.of(" abcdef ", 0, ((CharSequence) " abcdef ").length()).trimEnd().toString());
        assertEquals(" abcdef", BasedSequence.of(" abcdef", 0, ((CharSequence) " abcdef").length()).trimEnd().toString());
        assertEquals("abcdef", BasedSequence.of("abcdef   ", 0, ((CharSequence) "abcdef   ").length()).trimEnd().toString());
        assertEquals("abcdef", BasedSequence.of("abcdef ", 0, ((CharSequence) "abcdef ").length()).trimEnd().toString());
        assertEquals("abcdef", BasedSequence.of("abcdef", 0, ((CharSequence) "abcdef").length()).trimEnd().toString());
    }

    @Test
    public void test_trimmedEnd() throws Exception {
        assertEquals("       ", BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).trimmedEnd().toString());
        assertEquals("   ", BasedSequence.of("    abcdef   ", 0, ((CharSequence) "    abcdef   ").length()).trimmedEnd().toString());
        assertEquals(" ", BasedSequence.of("    abcdef ", 0, ((CharSequence) "    abcdef ").length()).trimmedEnd().toString());
        assertEquals("", BasedSequence.of("    abcdef", 0, ((CharSequence) "    abcdef").length()).trimmedEnd().toString());
        assertEquals("   ", BasedSequence.of(" abcdef   ", 0, ((CharSequence) " abcdef   ").length()).trimmedEnd().toString());
        assertEquals(" ", BasedSequence.of(" abcdef ", 0, ((CharSequence) " abcdef ").length()).trimmedEnd().toString());
        assertEquals("", BasedSequence.of(" abcdef", 0, ((CharSequence) " abcdef").length()).trimmedEnd().toString());
        assertEquals("   ", BasedSequence.of("abcdef   ", 0, ((CharSequence) "abcdef   ").length()).trimmedEnd().toString());
        assertEquals(" ", BasedSequence.of("abcdef ", 0, ((CharSequence) "abcdef ").length()).trimmedEnd().toString());
        assertEquals("", BasedSequence.of("abcdef", 0, ((CharSequence) "abcdef").length()).trimmedEnd().toString());
    }

    @Test
    public void test_trimEndKeep1() throws Exception {
        assertEquals(" ", BasedSequence.of("       ", 0, ((CharSequence) "       ").length()).trimEnd(1).toString());
        assertEquals("    abcdef ", BasedSequence.of("    abcdef   ", 0, ((CharSequence) "    abcdef   ").length()).trimEnd(1).toString());
        assertEquals("    abcdef ", BasedSequence.of("    abcdef ", 0, ((CharSequence) "    abcdef ").length()).trimEnd(1).toString());
        assertEquals("    abcdef", BasedSequence.of("    abcdef", 0, ((CharSequence) "    abcdef").length()).trimEnd(1).toString());
        assertEquals(" abcdef ", BasedSequence.of(" abcdef   ", 0, ((CharSequence) " abcdef   ").length()).trimEnd(1).toString());
        assertEquals(" abcdef ", BasedSequence.of(" abcdef ", 0, ((CharSequence) " abcdef ").length()).trimEnd(1).toString());
        assertEquals(" abcdef", BasedSequence.of(" abcdef", 0, ((CharSequence) " abcdef").length()).trimEnd(1).toString());
        assertEquals("abcdef ", BasedSequence.of("abcdef   ", 0, ((CharSequence) "abcdef   ").length()).trimEnd(1).toString());
        assertEquals("abcdef ", BasedSequence.of("abcdef ", 0, ((CharSequence) "abcdef ").length()).trimEnd(1).toString());
        assertEquals("abcdef", BasedSequence.of("abcdef", 0, ((CharSequence) "abcdef").length()).trimEnd(1).toString());
    }

    @Test
    public void test_startOfDelimitedBy() throws Exception {
        assertEquals(0, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 0));
        assertEquals(0, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 1));
        assertEquals(2, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 2));
        assertEquals(2, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 3));
        assertEquals(2, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 4));
        assertEquals(5, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 5));
        assertEquals(5, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 6));
        assertEquals(5, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 7));
        assertEquals(5, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 8));
        assertEquals(9, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 9));
        assertEquals(9, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 10));
        assertEquals(9, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 11));
        assertEquals(9, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 12));
        assertEquals(9, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 13));
        assertEquals(9, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedBy(",", 14));
    }

    @Test
    public void test_startOfDelimitedByAny() throws Exception {
        assertEquals(0, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 0));
        assertEquals(0, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 1));
        assertEquals(2, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 2));
        assertEquals(2, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 3));
        assertEquals(2, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 4));
        assertEquals(5, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 5));
        assertEquals(5, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 6));
        assertEquals(5, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 7));
        assertEquals(5, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 8));
        assertEquals(9, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 9));
        assertEquals(9, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 10));
        assertEquals(9, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 11));
        assertEquals(9, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 12));
        assertEquals(9, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 13));
        assertEquals(9, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).startOfDelimitedByAny(",", 14));
    }

    @Test
    public void test_endOfDelimitedBy() throws Exception {
        assertEquals(1, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 0));
        assertEquals(1, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 1));
        assertEquals(4, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 2));
        assertEquals(4, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 3));
        assertEquals(4, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 4));
        assertEquals(8, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 5));
        assertEquals(8, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 6));
        assertEquals(8, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 7));
        assertEquals(8, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 8));
        assertEquals(13, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 9));
        assertEquals(13, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 10));
        assertEquals(13, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 11));
        assertEquals(13, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 12));
        assertEquals(13, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 13));
        assertEquals(13, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedBy(",", 14));
    }

    @Test
    public void test_endOfDelimitedByAny() throws Exception {
        assertEquals(1, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 0));
        assertEquals(1, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 1));
        assertEquals(4, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 2));
        assertEquals(4, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 3));
        assertEquals(4, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 4));
        assertEquals(8, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 5));
        assertEquals(8, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 6));
        assertEquals(8, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 7));
        assertEquals(8, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 8));
        assertEquals(13, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 9));
        assertEquals(13, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 10));
        assertEquals(13, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 11));
        assertEquals(13, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 12));
        assertEquals(13, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 13));
        assertEquals(13, BasedSequence.of("0,23,567,9012", 0, ((CharSequence) "0,23,567,9012").length()).endOfDelimitedByAny(",", 14));
    }

    @Test
    public void testSplitBasic() throws Exception {
        BasedSequence sequence = BasedSequence.of(" 1,2 , 3 ,4,5,   ", 0, ((CharSequence) " 1,2 , 3 ,4,5,   ").length());
        BasedSequence[] list = sequence.split(',', 0, BasedSequence.SPLIT_TRIM_PARTS | BasedSequence.SPLIT_SKIP_EMPTY);
        ArrayList<String> sl = new ArrayList<>(list.length);
        for (BasedSequence basedSequence : list) sl.add(basedSequence.toString());

        assertArrayEquals(new String[] { "1", "2", "3", "4", "5" }, sl.toArray(new String[0]));
    }

    @Test
    public void testSplitEol() throws Exception {
        BasedSequence sequence = BasedSequence.of("   line1 \nline2 \n line3 \n", 0, ((CharSequence) "   line1 \nline2 \n line3 \n").length());
        BasedSequence[] list = sequence.split('\n', 0, BasedSequence.SPLIT_INCLUDE_DELIMS);
        ArrayList<String> sl = new ArrayList<>(list.length);
        for (BasedSequence basedSequence : list) sl.add(basedSequence.toString());

        assertArrayEquals(new String[] { "   line1 \n", "line2 \n", " line3 \n" }, sl.toArray(new String[0]));
    }

    @Test
    public void testPrefixOf() {
        BasedSequence of = BasedSequence.of("123", 0, ((CharSequence) "123").length());
        assertEquals(of.subSequence(0, 0), of.baseSubSequence(0, 0).prefixOf(of.subSequence(0, 0)));
        assertEquals(of.subSequence(0, 0), of.baseSubSequence(0, 0).prefixOf(of.subSequence(0, 1)));

        assertEquals(of.subSequence(0, 0), of.baseSubSequence(0, 1).prefixOf(of.subSequence(0, 1)));
        assertEquals(of.subSequence(0, 1), of.baseSubSequence(0, 1).prefixOf(of.subSequence(1, 1)));

        assertEquals(of.subSequence(0, 1), of.baseSubSequence(0, 3).prefixOf(of.subSequence(1, 1)));

        assertEquals(of.subSequence(0, 1), of.baseSubSequence(0, 2).prefixOf(of.subSequence(1, 2)));
        assertEquals(of.subSequence(0, 1), of.baseSubSequence(0, 1).prefixOf(of.subSequence(1, 1)));
    }

    @Test
    public void testSuffixOf() {
        BasedSequence of = BasedSequence.of("123", 0, ((CharSequence) "123").length());
        assertEquals(of.subSequence(3, 3), of.baseSubSequence(3, 3).suffixOf(of.subSequence(3, 3)));
        assertEquals(of.subSequence(3, 3), of.baseSubSequence(3, 3).suffixOf(of.subSequence(2, 3)));

        assertEquals(of.subSequence(3, 3), of.baseSubSequence(2, 3).suffixOf(of.subSequence(2, 3)));
        assertEquals(of.subSequence(2, 3), of.baseSubSequence(2, 3).suffixOf(of.subSequence(2, 2)));

        assertEquals(of.subSequence(1, 3), of.baseSubSequence(0, 3).suffixOf(of.subSequence(1, 1)));

        assertEquals(of.subSequence(2, 2), of.baseSubSequence(0, 2).suffixOf(of.subSequence(1, 2)));
        assertEquals(of.subSequence(1, 1), of.baseSubSequence(0, 1).suffixOf(of.subSequence(1, 1)));
    }

    @Test
    public void testReplace() {
        BasedSequence text = BasedSequence.of("[foo]", 0, ((CharSequence) "[foo]").length());
        assertEquals(BasedSequence.of("[bar]", 0, ((CharSequence) "[bar]").length()), text.replace("foo", "bar"));
        assertEquals(BasedSequence.of("[foo]", 0, ((CharSequence) "[foo]").length()), text.replace("food", "bars"));
        assertEquals(BasedSequence.of("(foo]", 0, ((CharSequence) "(foo]").length()), text.replace("[", "("));
        assertEquals(BasedSequence.of("[foo)", 0, ((CharSequence) "[foo)").length()), text.replace("]", ")"));
    }

    @Test
    public void test_getLineColumnAtIndex() {
        String[] lines = new String[] {
            "1: line 1\n",
            "2: line 2\n",
            "3: line 3\r",
            "4: line 4\r\n",
            "5: line 5\r",
            "6: line 6"
        };

        int iMax = lines.length;

        int[] lineStarts = new int[iMax + 1];
        int[] lineEnds = new int[iMax];
        int len = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iMax; i++) {
            lineStarts[i] = len;
            String line = lines[i].replaceAll("\r|\n", "");
            lineEnds[i] = len + line.length();
            len += lines[i].length();
            sb.append(lines[i]);
        }

        lineStarts[iMax] = len;

        int jMax = len;
        List<Pair<Integer, Integer>> info = new ArrayList<>(jMax);

        for (int j = 0; j < jMax; j++) {
            for (int i = 0; i < iMax; i++) {
                if (j >= lineStarts[i] && j < lineStarts[i + 1]) {
                    int col = j - lineStarts[i];
                    int line = i;
                    if (j > lineEnds[i]) {
                        col = 0;
                        line++;
                    }
                    info.add(new Pair<>(line, col));
                    //System.out.println(String.format("%d: [%d, %d]", j, line, col));
                    break;
                }
            }
        }

        assertEquals(jMax, info.size());

        CharSequence charSequence = sb.toString();
        BasedSequence text = BasedSequence.of(charSequence, 0, charSequence.length());

        for (int j = 0; j < jMax; j++) {
            final Pair<Integer, Integer> atIndex = text.lineColumnAtIndex(j);
            if (!info.get(j).equals(atIndex)) {
                text.lineColumnAtIndex(j);
            }
            assertEquals("Failed at " + j, info.get(j), atIndex);
        }
    }

    @Test
    public void test_trimEndTo() {
        assertEquals("", BasedSequence.of("", 0, ((CharSequence) "").length()).trimEnd(0, ".\t").toString());
        assertEquals("", BasedSequence.of(".", 0, ((CharSequence) ".").length()).trimEnd(0, ".\t").toString());
        assertEquals("", BasedSequence.of("..", 0, ((CharSequence) "..").length()).trimEnd(0, ".\t").toString());
        assertEquals(".", BasedSequence.of("..", 0, ((CharSequence) "..").length()).trimEnd(1, ".\t").toString());
        assertEquals(".", BasedSequence.of(".", 0, ((CharSequence) ".").length()).trimEnd(1, ".\t").toString());
        assertEquals(".", BasedSequence.of(".", 0, ((CharSequence) ".").length()).trimEnd(2, ".\t").toString());
        assertEquals("abc", BasedSequence.of("abc", 0, ((CharSequence) "abc").length()).trimEnd(0, ".\t").toString());
        assertEquals(".abc", BasedSequence.of(".abc", 0, ((CharSequence) ".abc").length()).trimEnd(0, ".\t").toString());
        assertEquals("..abc", BasedSequence.of("..abc", 0, ((CharSequence) "..abc").length()).trimEnd(0, ".\t").toString());
        assertEquals("..abc", BasedSequence.of("..abc.", 0, ((CharSequence) "..abc.").length()).trimEnd(0, ".\t").toString());
        assertEquals("..abc", BasedSequence.of("..abc..", 0, ((CharSequence) "..abc..").length()).trimEnd(0, ".\t").toString());
        assertEquals("..abc..", BasedSequence.of("..abc..", 0, ((CharSequence) "..abc..").length()).trimEnd(2, ".\t").toString());
        assertEquals("..abc..", BasedSequence.of("..abc..", 0, ((CharSequence) "..abc..").length()).trimEnd(3, ".\t").toString());
        assertEquals("..abc..", BasedSequence.of("..abc....", 0, ((CharSequence) "..abc....").length()).trimEnd(2, ".\t").toString());
        assertEquals("..abc.", BasedSequence.of("..abc....", 0, ((CharSequence) "..abc....").length()).trimEnd(1, ".\t").toString());
        assertEquals("..abc...", BasedSequence.of("..abc....", 0, ((CharSequence) "..abc....").length()).trimEnd(3, ".\t").toString());
    }

    @Test
    public void test_trimStartTo() {
        assertEquals("", BasedSequence.of("", 0, ((CharSequence) "").length()).trimStart(0, ".\t").toString());
        assertEquals("", BasedSequence.of(".", 0, ((CharSequence) ".").length()).trimStart(0, ".\t").toString());
        assertEquals("", BasedSequence.of("..", 0, ((CharSequence) "..").length()).trimStart(0, ".\t").toString());
        assertEquals(".", BasedSequence.of("..", 0, ((CharSequence) "..").length()).trimStart(1, ".\t").toString());
        assertEquals(".", BasedSequence.of(".", 0, ((CharSequence) ".").length()).trimStart(1, ".\t").toString());
        assertEquals(".", BasedSequence.of(".", 0, ((CharSequence) ".").length()).trimStart(2, ".\t").toString());
        assertEquals("..", BasedSequence.of("...", 0, ((CharSequence) "...").length()).trimStart(2, ".\t").toString());
        assertEquals("..", BasedSequence.of("....", 0, ((CharSequence) "....").length()).trimStart(2, ".\t").toString());
        assertEquals("abc", BasedSequence.of("abc", 0, ((CharSequence) "abc").length()).trimStart(0, ".\t").toString());
        assertEquals("abc.", BasedSequence.of("abc.", 0, ((CharSequence) "abc.").length()).trimStart(0, ".\t").toString());
        assertEquals("abc..", BasedSequence.of(".abc..", 0, ((CharSequence) ".abc..").length()).trimStart(0, ".\t").toString());
        assertEquals("abc..", BasedSequence.of("..abc..", 0, ((CharSequence) "..abc..").length()).trimStart(0, ".\t").toString());
        assertEquals("abc..", BasedSequence.of(".abc..", 0, ((CharSequence) ".abc..").length()).trimStart(0, ".\t").toString());
        assertEquals("abc..", BasedSequence.of(".abc..", 0, ((CharSequence) ".abc..").length()).trimStart(0, ".\t").toString());
        assertEquals("abc..", BasedSequence.of(".abc..", 0, ((CharSequence) ".abc..").length()).trimStart(0, ".\t").toString());
        assertEquals(".abc..", BasedSequence.of("..abc..", 0, ((CharSequence) "..abc..").length()).trimStart(1, ".\t").toString());
        assertEquals("..abc..", BasedSequence.of("...abc..", 0, ((CharSequence) "...abc..").length()).trimStart(2, ".\t").toString());
        assertEquals("...abc..", BasedSequence.of("...abc..", 0, ((CharSequence) "...abc..").length()).trimStart(3, ".\t").toString());
    }

    @Test
    public void test_indexOfAll() throws Exception {
        final String s1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        BasedSequence s = BasedSequence.of(s1, 0, ((CharSequence) s1).length());

        int[] indices = s.indexOfAll("a");

        assertEquals(33, indices.length);

        for (int i = 0; i < indices.length; i++) {
            assertEquals(i, indices[i]);
        }
    }

    @Test
    public void test_indexOfAll2() throws Exception {
        final String s1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        BasedSequence s = BasedSequence.of(s1, 0, ((CharSequence) s1).length());

        int[] indices = s.indexOfAll("a");

        assertEquals(66, indices.length);

        for (int i = 0; i < indices.length; i++) {
            assertEquals(i, indices[i]);
        }
    }

    @Test
    public void test_extendByOneOfAny() {
        BasedSequence b = BasedSequence.of("this;.,\n", 0, ((CharSequence) "this;.,\n").length());
        BasedSequence s = b.subSequence(0, 4);

        assertEquals("this", s.extendByOneOfAny("").toString());
        assertEquals("this", s.extendByOneOfAny("-*").toString());
        assertEquals("this;", s.extendByOneOfAny(";").toString());
        assertEquals("this;", s.extendByOneOfAny(".;").toString());
        assertEquals("this;", s.extendByOneOfAny(",.;").toString());
        assertEquals("this;", s.extendByOneOfAny("\n,.;").toString());
    }

    @Test
    public void test_extendByAny() {
        BasedSequence b = BasedSequence.of("this;.,\n", 0, ((CharSequence) "this;.,\n").length());
        BasedSequence s = b.subSequence(0, 4);

        assertEquals("this", s.extendByAny("").toString());
        assertEquals("this", s.extendByAny("-*").toString());
        assertEquals("this;", s.extendByAny(";").toString());
        assertEquals("this;.", s.extendByAny(".;").toString());
        assertEquals("this;.,", s.extendByAny(",.;").toString());
        assertEquals("this;.,\n", s.extendByAny("\n,.;").toString());
    }

    @Test
    public void test_extendByAny2() {
        BasedSequence b = BasedSequence.of("this;.,\n", 0, ((CharSequence) "this;.,\n").length());
        BasedSequence s = b.subSequence(0, 4);

        assertEquals("this", s.extendByAny("", 2).toString());
        assertEquals("this", s.extendByAny("-*", 2).toString());
        assertEquals("this;", s.extendByAny(";", 2).toString());
        assertEquals("this;.", s.extendByAny(".;", 2).toString());
        assertEquals("this;.", s.extendByAny(",.;", 2).toString());
        assertEquals("this;.", s.extendByAny("\n,.;", 2).toString());
    }

    @Test
    public void test_extendToAny() {
        BasedSequence b = BasedSequence.of("this;.,\n", 0, ((CharSequence) "this;.,\n").length());
        BasedSequence s = b.subSequence(0, 4);

        assertEquals("this", s.extendToAny("").toString());
        assertEquals("this", s.extendToAny("-*").toString());
        assertEquals("this;", s.extendToAny(";").toString());
        assertEquals("this;", s.extendToAny(".;").toString());
        assertEquals("this;.", s.extendToAny(".").toString());
        assertEquals("this;.", s.extendToAny(".,").toString());
        assertEquals("this;.", s.extendToAny(",.").toString());
        assertEquals("this;.", s.extendToAny(",.").toString());
        assertEquals("this;.,", s.extendToAny(",").toString());
        assertEquals("this;", s.extendToAny("\n,.;").toString());
        assertEquals("this;.", s.extendToAny("\n,.").toString());
        assertEquals("this;.,", s.extendToAny("\n,").toString());
    }

    @Test
    public void test_prefixWithIndent() {
        assertEquals("test\n", BasedSequence.of("\ntest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals(" test\n", BasedSequence.of("\n test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("  test\n", BasedSequence.of("\n  test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("   test\n", BasedSequence.of("\n   test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("    test\n", BasedSequence.of("\n    test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("     test\n", BasedSequence.of("\n     test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("      test\n", BasedSequence.of("\n      test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
    }

    @Test
    public void test_prefixWithIndent0() {
        assertEquals("test\n", BasedSequence.of("\ntest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n  test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n   test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n    test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n     test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n      test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
    }

    @Test
    public void test_prefixWithIndent4() {
        assertEquals("test\n", BasedSequence.of("\ntest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(4).toString());
        assertEquals(" test\n", BasedSequence.of("\n test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(4).toString());
        assertEquals("  test\n", BasedSequence.of("\n  test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(4).toString());
        assertEquals("   test\n", BasedSequence.of("\n   test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(4).toString());
        assertEquals("    test\n", BasedSequence.of("\n    test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(4).toString());
        assertEquals("    test\n", BasedSequence.of("\n     test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(4).toString());
        assertEquals("    test\n", BasedSequence.of("\n      test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(4).toString());
    }

    @Test
    public void test_prefixWithIndentTabs() {
        assertEquals("\ttest\n", BasedSequence.of("\n\ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals(" \ttest\n", BasedSequence.of("\n \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("  \ttest\n", BasedSequence.of("\n  \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("   \ttest\n", BasedSequence.of("\n   \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("    \ttest\n", BasedSequence.of("\n    \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("     \ttest\n", BasedSequence.of("\n     \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("      \ttest\n", BasedSequence.of("\n      \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("\ttest\n", BasedSequence.of("\n\ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("\t test\n", BasedSequence.of("\n\t test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("\t  test\n", BasedSequence.of("\n\t  test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("\t   test\n", BasedSequence.of("\n\t   test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("\t    test\n", BasedSequence.of("\n\t    test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("\t     test\n", BasedSequence.of("\n\t     test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
        assertEquals("\t      test\n", BasedSequence.of("\n\t      test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent().toString());
    }

    @Test
    public void test_prefixWithIndentTabs0() {
        assertEquals("test\n", BasedSequence.of("\n\ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n  \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n   \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n    \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n     \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n      \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n\ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n\t test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n\t  test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n\t   test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n\t    test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n\t     test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
        assertEquals("test\n", BasedSequence.of("\n\t      test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(0).toString());
    }

    @Test
    public void test_prefixWithIndentTabs1to8() {
        assertEquals("test\n", BasedSequence.of("\n\ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(1).toString());
        assertEquals("test\n", BasedSequence.of("\n \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(2).toString());
        assertEquals(" \ttest\n", BasedSequence.of("\n  \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(3).toString());
        assertEquals("   \ttest\n", BasedSequence.of("\n   \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(4).toString());
        assertEquals(" \ttest\n", BasedSequence.of("\n    \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(5).toString());
        assertEquals("   \ttest\n", BasedSequence.of("\n     \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(6).toString());
        assertEquals("     \ttest\n", BasedSequence.of("\n      \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(7).toString());
        assertEquals("       \ttest\n", BasedSequence.of("\n       \ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(8).toString());

        assertEquals("test\n", BasedSequence.of("\n\ttest\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(1).toString());
        assertEquals(" test\n", BasedSequence.of("\n\t test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(2).toString());
        assertEquals("  test\n", BasedSequence.of("\n\t  test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(3).toString());
        assertEquals("   test\n", BasedSequence.of("\n\t   test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(4).toString());
        assertEquals("    test\n", BasedSequence.of("\n\t    test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(5).toString());
        assertEquals("     test\n", BasedSequence.of("\n\t     test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(6).toString());
        assertEquals("      test\n", BasedSequence.of("\n\t      test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(7).toString());
        assertEquals("       test\n", BasedSequence.of("\n\t       test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(8).toString());
        assertEquals("\t    test\n", BasedSequence.of("\n\t    test\n").trimStart(BasedSequence.WHITESPACE_CHARS).prefixWithIndent(8).toString());
    }

    @Test
    public void test_trimEOL() {
        BasedSequence s = BasedSequence.of("abc\n");
        assertEquals("abc", s.trimEOL().toString());
        assertEquals(s.subSequence(0, 3).getSourceRange(), s.trimEOL().getSourceRange());
        assertEquals("\n", s.trimmedEOL().toString());
        assertEquals(s.subSequence(3, 4).getSourceRange(), s.trimmedEOL().getSourceRange());
    }

    @Test
    public void test_trimEOL1() {
        BasedSequence s = BasedSequence.of("abc\n   ");
        assertEquals("abc\n   ", s.trimEOL().toString());
        assertEquals(s.subSequence(0, 7).getSourceRange(), s.trimEOL().getSourceRange());
        assertEquals("", s.trimmedEOL().toString());
        assertSame(BasedSequence.NULL, s.trimmedEOL());
        assertSame(Range.NULL, s.trimmedEOL().getSourceRange());
    }

    @Test
    public void test_trimEOL3() {
        BasedSequence s = BasedSequence.of("abc\ndef");
        assertEquals("abc\ndef", s.trimEOL().toString());
        assertEquals(s.subSequence(0, 7).getSourceRange(), s.trimEOL().getSourceRange());
        assertEquals("", s.trimmedEOL().toString());
        assertSame(BasedSequence.NULL, s.trimmedEOL());
    }

    @Test
    public void test_trimNoEOL() {
        BasedSequence s = BasedSequence.of("abc");
        assertEquals("abc", s.trimEOL().toString());
        assertEquals(s.subSequence(0, 3).getSourceRange(), s.trimEOL().getSourceRange());
        assertEquals(BasedSequence.NULL, s.trimmedEOL());
    }

    @Test
    public void test_trimMultiEOL() {
        BasedSequence s = BasedSequence.of("abc\n\n");
        assertEquals("abc\n", s.trimEOL().toString());
        assertEquals(s.subSequence(0, 4).getSourceRange(), s.trimEOL().getSourceRange());
        assertEquals("\n", s.trimmedEOL().toString());
        assertEquals(s.subSequence(4, 5).getSourceRange(), s.trimmedEOL().getSourceRange());
    }

    @Test
    public void test_trimMultiEOL2() {
        BasedSequence s = BasedSequence.of("abc\n\n   ");
        assertEquals("abc\n\n   ", s.trimEOL().toString());
        assertEquals(s.subSequence(0, 8).getSourceRange(), s.trimEOL().getSourceRange());
        assertEquals("", s.trimmedEOL().toString());
        assertEquals(Range.NULL, s.trimmedEOL().getSourceRange());
    }

    @Test
    public void test_trimTailBlankLines() {
        assertEquals("", BasedSequence.of("   ").trimTailBlankLines().toString());
        assertEquals("", BasedSequence.of("\n   ").trimTailBlankLines().toString());
        assertEquals("", BasedSequence.of("\n   \n").trimTailBlankLines().toString());
        assertEquals("", BasedSequence.of("   \n").trimTailBlankLines().toString());
        assertEquals("   t", BasedSequence.of("   t").trimTailBlankLines().toString());
        assertEquals("t\n", BasedSequence.of("t\n   ").trimTailBlankLines().toString());
        assertEquals("\n   t\n", BasedSequence.of("\n   t\n").trimTailBlankLines().toString());
        assertEquals("t   \n", BasedSequence.of("t   \n").trimTailBlankLines().toString());
        assertEquals("\n\t    test\n", BasedSequence.of("\n\t    test\n").trimTailBlankLines().toString());
        assertEquals("\n\n\t    test\n", BasedSequence.of("\n\n\t    test\n").trimTailBlankLines().toString());
        assertEquals("\n\n\t    test\n", BasedSequence.of("\n\n\t    test\n\n").trimTailBlankLines().toString());
        assertEquals("\n\n\t    test\n", BasedSequence.of("\n\n\t    test\n     \n").trimTailBlankLines().toString());
        assertEquals("\n\n\t    test\n", BasedSequence.of("\n\n\t    test\n     \n\n\t\n").trimTailBlankLines().toString());
        assertEquals("\n\n\t    test\n", BasedSequence.of("\n\n\t    test\n     \n\n\t\n\t     ").trimTailBlankLines().toString());
        assertEquals("\n\n\t    test   \n", BasedSequence.of("\n\n\t    test   \n     \n\n\t\n\t     ").trimTailBlankLines().toString());
    }

    @Test
    public void test_trimLeadBlankLines() {
        assertEquals("   t", BasedSequence.of("   t").trimLeadBlankLines().toString());
        assertEquals("", BasedSequence.of("   ").trimLeadBlankLines().toString());
        assertEquals("", BasedSequence.of("   \n").trimLeadBlankLines().toString());
        assertEquals("\t    test\n", BasedSequence.of("\n\t    test\n").trimLeadBlankLines().toString());
        assertEquals("\t    test\n", BasedSequence.of("\n  \n\t    test\n").trimLeadBlankLines().toString());
        assertEquals("\t    test\n\n", BasedSequence.of("\n\t  \n\t    test\n\n").trimLeadBlankLines().toString());
        assertEquals("\t    test\n     \n", BasedSequence.of("\n \t \n\t    test\n     \n").trimLeadBlankLines().toString());
        assertEquals("\t    test\n     \n\n\t\n", BasedSequence.of("\n\n\t    test\n     \n\n\t\n").trimLeadBlankLines().toString());
        assertEquals("\t    test\n     \n\n\t\n\t     ", BasedSequence.of("\n   \t\n\t    test\n     \n\n\t\n\t     ").trimLeadBlankLines().toString());
        assertEquals("test\n     \n\n\t\n\t     ", BasedSequence.of("\n   \t\n\t    \ntest\n     \n\n\t\n\t     ").trimLeadBlankLines().toString());
    }

    @Test
    public void test_blankLinesRange() {
        assertEquals(Range.of(0, 1), BasedSequence.of("\n\t    test\n").leadingBlankLinesRange());
        assertEquals(Range.of(0, 4), BasedSequence.of("\n  \n\t    test\n").leadingBlankLinesRange());
        assertEquals(Range.of(0, 5), BasedSequence.of("\n\t  \n\t    test\n\n").leadingBlankLinesRange());
        assertEquals(Range.of(0, 5), BasedSequence.of("\n \t \n\t    test\n     \n").leadingBlankLinesRange());
        assertEquals(Range.of(0, 2), BasedSequence.of("\n\n\t    test\n     \n\n\t\n").leadingBlankLinesRange());
        assertEquals(Range.of(0, 6), BasedSequence.of("\n   \t\n\t    test\n     \n\n\t\n\t     ").leadingBlankLinesRange());
    }

    @Test
    public void test_lastBlankLinesRange() {
        assertSame(Range.NULL, BasedSequence.of("\n\t    test\n").trailingBlankLinesRange());
        assertSame(Range.NULL, BasedSequence.of("\n\n\t    test\n").trailingBlankLinesRange());
        assertEquals(Range.of(12, 13), BasedSequence.of("\n\n\t    test\n\n").trailingBlankLinesRange());
        assertEquals(Range.of(12, 18), BasedSequence.of("\n\n\t    test\n     \n").trailingBlankLinesRange());
        assertEquals(Range.of(12, 21), BasedSequence.of("\n\n\t    test\n     \n\n\t\n").trailingBlankLinesRange());
        assertEquals(Range.of(12, 27), BasedSequence.of("\n\n\t    test\n     \n\n\t\n\t     ").trailingBlankLinesRange());
    }

    @Test
    public void test_removeBlankLinesRanges1() {
        String input = "\n" +
            "\t    test\n" +
            "\n" +
            "\n" +
            "";

        String result = "" +
            "\t    test\n" +
            "";

        BasedSequence sequence = BasedSequence.of(input);
        assertEquals(result, sequence.extractRanges(sequence.blankLinesRemovedRanges()).toString());
    }

    @Test
    public void test_removeBlankLinesRanges2() {
        String input = "\n" +
            "\t    test\n" +
            "\n" +
            "    t\n" +
            "\n" +
            "";

        String result = "" +
            "\t    test\n" +
            "    t\n" +
            "";

        BasedSequence sequence = BasedSequence.of(input);
        assertEquals(result, sequence.extractRanges(sequence.blankLinesRemovedRanges()).toString());
    }

    @Test
    public void test_removeBlankLinesRanges3() {
        String input = "\n" +
            "\t    test\n" +
            "\n" +
            "    t1\n" +
            "\n" +
            "    t2\n" +
            "\n" +
            "\n" +
            "\n" +
            "    t3\n" +
            "\n" +
            "    t4\n" +
            "\n" +
            "";

        String result = "" +
            "\t    test\n" +
            "    t1\n" +
            "    t2\n" +
            "    t3\n" +
            "    t4\n" +
            "";

        BasedSequence sequence = BasedSequence.of(input);
        assertEquals(result, sequence.extractRanges(sequence.blankLinesRemovedRanges()).toString());
    }

    @Test
    public void test_extendToEndOfLine() {
        String input = "" +
            "0123456789\n" +
            "abcdefghij\n" +
            "\n";

        BasedSequence sequence = BasedSequence.of(input);

        assertEquals("0123456789", sequence.subSequence(0, 0).extendToEndOfLine().toString());
        assertEquals("123456789", sequence.subSequence(1, 9).extendToEndOfLine().toString());
        assertEquals("bcdefghij", sequence.subSequence(12, 13).extendToEndOfLine().toString());
        assertEquals("", sequence.subSequence(22, 22).extendToEndOfLine().toString());
        assertEquals("\n", sequence.subSequence(22, 23).extendToEndOfLine().toString());

        assertEquals("0123456789\n", sequence.subSequence(0, 0).extendToEndOfLine(true).toString());
        assertEquals("123456789\n", sequence.subSequence(1, 9).extendToEndOfLine(true).toString());
        assertEquals("bcdefghij\n", sequence.subSequence(12, 13).extendToEndOfLine(true).toString());
        assertEquals("\n", sequence.subSequence(22, 22).extendToEndOfLine(true).toString());
        assertEquals("\n", sequence.subSequence(22, 23).extendToEndOfLine(true).toString());
    }

    @Test
    public void test_extendToStartOfLine() {
        String input = "" +
            "0123456789\n" +
            "abcdefghij\n" +
            "\n";

        BasedSequence sequence = BasedSequence.of(input);

        assertEquals("", sequence.subSequence(0, 0).extendToStartOfLine().toString());
        assertEquals("012345678", sequence.subSequence(1, 9).extendToStartOfLine().toString());
        assertEquals("ab", sequence.subSequence(12, 13).extendToStartOfLine().toString());
        assertEquals("", sequence.subSequence(22, 22).extendToStartOfLine().toString());
        assertEquals("\n", sequence.subSequence(22, 23).extendToStartOfLine().toString());

        assertEquals("", sequence.subSequence(0, 0).extendToStartOfLine(true).toString());
        assertEquals("012345678", sequence.subSequence(1, 9).extendToStartOfLine(true).toString());
        assertEquals("\nab", sequence.subSequence(12, 13).extendToStartOfLine(true).toString());
        assertEquals("\n", sequence.subSequence(22, 22).extendToStartOfLine(true).toString());
        assertEquals("\n", sequence.subSequence(22, 23).extendToStartOfLine(true).toString());
    }

    @Test
    public void test_insertEmpty() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);

        assertSame(sequence, sequence.insert(0, ""));
        assertSame(sequence, sequence.insert(20, ""));
        assertSame(sequence, sequence.insert(-5, ""));
    }

    @Test
    public void test_insertPrefix() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence inserted = sequence.insert(0, "^");

        assertTrue(inserted instanceof PrefixedSubSequence);
        assertEquals("^0123456789", inserted.toString());
        assertEquals(Range.of(0, 10), inserted.getSourceRange());
        assertEquals(-1, inserted.getIndexOffset(0));
        assertEquals(0, inserted.getIndexOffset(1));
        assertEquals(1, inserted.getIndexOffset(2));
        assertEquals(8, inserted.getIndexOffset(9));
        assertEquals(9, inserted.getIndexOffset(10));
        assertEquals(10, inserted.getIndexOffset(11));
    }

    @Test
    public void test_insertSuffix() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence inserted = sequence.insert(10, "^");
        assertEquals("0123456789^", inserted.toString());
        assertTrue(inserted instanceof SegmentedSequence);
        assertEquals(Range.of(0, 10), inserted.getSourceRange());
        assertEquals(0, inserted.getIndexOffset(0));
        assertEquals(1, inserted.getIndexOffset(1));
        assertEquals(2, inserted.getIndexOffset(2));
        assertEquals(9, inserted.getIndexOffset(9));
        assertEquals(-1, inserted.getIndexOffset(10));
        assertEquals(-1, inserted.getIndexOffset(11));
    }

    @Test
    public void test_insertMiddle1() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence inserted = sequence.insert(1, "^");
        assertEquals("0^123456789", inserted.toString());
        assertTrue(inserted instanceof SegmentedSequence);
        assertEquals(Range.of(0, 10), inserted.getSourceRange());
        assertEquals(0, inserted.getIndexOffset(0));
        assertEquals(-1, inserted.getIndexOffset(1));
        assertEquals(1, inserted.getIndexOffset(2));
        assertEquals(2, inserted.getIndexOffset(3));
        assertEquals(9, inserted.getIndexOffset(10));
        assertEquals(10, inserted.getIndexOffset(11));
    }

    @Test
    public void test_insertMiddle5() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence inserted = sequence.insert(5, "^");
        assertEquals("01234^56789", inserted.toString());
        assertTrue(inserted instanceof SegmentedSequence);
        assertEquals(Range.of(0, 10), inserted.getSourceRange());
        assertEquals(0, inserted.getIndexOffset(0));
        assertEquals(1, inserted.getIndexOffset(1));
        assertEquals(4, inserted.getIndexOffset(4));
        assertEquals(-1, inserted.getIndexOffset(5));
        assertEquals(5, inserted.getIndexOffset(6));
        assertEquals(9, inserted.getIndexOffset(10));
        assertEquals(10, inserted.getIndexOffset(11));
    }

    @Test
    public void test_insertMiddle9() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence inserted = sequence.insert(9, "^");
        assertEquals("012345678^9", inserted.toString());
        assertTrue(inserted instanceof SegmentedSequence);
        assertEquals(Range.of(0, 10), inserted.getSourceRange());
        assertEquals(0, inserted.getIndexOffset(0));
        assertEquals(1, inserted.getIndexOffset(1));
        assertEquals(8, inserted.getIndexOffset(8));
        assertEquals(-1, inserted.getIndexOffset(9));
        assertEquals(9, inserted.getIndexOffset(10));
        assertEquals(10, inserted.getIndexOffset(11));
    }

    @Test
    public void test_replacePrefix() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.replace(0, 0, "^");

        assertEquals("^0123456789", replaced.toString());
        assertEquals(Range.of(0, 10), replaced.getSourceRange());
        assertEquals(-1, replaced.getIndexOffset(0));
        assertEquals(0, replaced.getIndexOffset(1));
        assertEquals(1, replaced.getIndexOffset(2));
        assertEquals(8, replaced.getIndexOffset(9));
        assertEquals(9, replaced.getIndexOffset(10));
        assertEquals(10, replaced.getIndexOffset(11));
    }

    @Test
    public void test_replacePrefix1() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.replace(0, 1, "^");

        assertEquals("^123456789", replaced.toString());
        assertEquals(Range.of(0, 10), replaced.getSourceRange());
        assertEquals(-1, replaced.getIndexOffset(0));
        assertEquals(1, replaced.getIndexOffset(1));
        assertEquals(2, replaced.getIndexOffset(2));
        assertEquals(9, replaced.getIndexOffset(9));
        assertEquals(10, replaced.getIndexOffset(10));
    }

    @Test
    public void test_replaceSuffix() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.replace(10, 10, "^");
        assertEquals("0123456789^", replaced.toString());
        assertEquals(Range.of(0, 10), replaced.getSourceRange());
        assertEquals(0, replaced.getIndexOffset(0));
        assertEquals(1, replaced.getIndexOffset(1));
        assertEquals(2, replaced.getIndexOffset(2));
        assertEquals(9, replaced.getIndexOffset(9));
        assertEquals(-1, replaced.getIndexOffset(10));
    }

    @Test
    public void test_replaceSuffix9() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.replace(9, 10, "^");
        assertEquals("012345678^", replaced.toString());
        assertEquals(Range.of(0, 10), replaced.getSourceRange());
        assertEquals(0, replaced.getIndexOffset(0));
        assertEquals(1, replaced.getIndexOffset(1));
        assertEquals(2, replaced.getIndexOffset(2));
        assertEquals(8, replaced.getIndexOffset(8));
        assertEquals(-1, replaced.getIndexOffset(9));
        assertEquals(-1, replaced.getIndexOffset(10));
    }

    @Test
    public void test_replaceMiddle1() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.replace(1, 2, "^");
        assertEquals("0^23456789", replaced.toString());
        assertEquals(Range.of(0, 10), replaced.getSourceRange());
        assertEquals(0, replaced.getIndexOffset(0));
        assertEquals(-1, replaced.getIndexOffset(1));
        assertEquals(2, replaced.getIndexOffset(2));
        assertEquals(3, replaced.getIndexOffset(3));
        assertEquals(10, replaced.getIndexOffset(10));
    }

    @Test
    public void test_replaceMiddle5() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.replace(5, 6, "^");
        assertEquals("01234^6789", replaced.toString());
        assertEquals(Range.of(0, 10), replaced.getSourceRange());
        assertEquals(0, replaced.getIndexOffset(0));
        assertEquals(1, replaced.getIndexOffset(1));
        assertEquals(4, replaced.getIndexOffset(4));
        assertEquals(-1, replaced.getIndexOffset(5));
        assertEquals(6, replaced.getIndexOffset(6));
        assertEquals(10, replaced.getIndexOffset(10));
    }

    @Test
    public void text_prefixWith() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.prefixWith("<");
        assertEquals("<0123456789", replaced.toString());
    }

    @Test
    public void text_prefixWith2() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.prefixWith("<").prefixWith("<");
        assertEquals("<<0123456789", replaced.toString());
    }

    @Test
    public void test_suffixWith() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.suffixWith(">");
        assertEquals("0123456789>", replaced.toString());
    }

    @Test
    public void test_suffixWith2() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.suffixWith(">").suffixWith(">");
        assertEquals("0123456789>>", replaced.toString());
    }

    @Test
    public void test_prefixOnceWith() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.prefixOnceWith("<");
        assertEquals("<0123456789", replaced.toString());
    }

    @Test
    public void test_prefixOnceWith2() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.prefixOnceWith("<").prefixOnceWith("<");
        assertEquals("<0123456789", replaced.toString());
    }

    @Test
    public void test_suffixOnceWith() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.suffixOnceWith(">");
        assertEquals("0123456789>", replaced.toString());
    }

    @Test
    public void test_suffixOnceWith2() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.suffixOnceWith(">").suffixOnceWith(">");
        assertEquals("0123456789>", replaced.toString());
    }

    @Test
    public void test_replaceChars1() {
        String input = "01234567890123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.replace("012", "abcd");
        assertEquals("abcd3456789abcd3456789", replaced.toString());
    }

    @Test
    public void test_replaceChars2() {
        String input = "01234567890123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.replace("6789", "xyz");
        assertEquals("012345xyz012345xyz", replaced.toString());
    }

    @Test
    public void test_append() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.append("abcd", "xyz");
        assertEquals("0123456789abcdxyz", replaced.toString());
    }

    @Test
    public void test_extractRanges() {
        String input = "0123456789";

        BasedSequence sequence = BasedSequence.of(input);
        BasedSequence replaced = sequence.extractRanges(Range.of(0, 0), Range.of(0, 1), Range.of(3, 6), Range.of(8, 12));
        assertEquals("034589", replaced.toString());
    }

    @Test
    public void test_eolEndRange1() {
        String input = "\n1234\n6789\n";

        BasedSequence sequence = BasedSequence.of(input);
        assertSame(Range.NULL, sequence.eolEndRange(0));
        assertEquals(Range.of(0, 1), sequence.eolEndRange(1));
        assertSame(Range.NULL, sequence.eolEndRange(2));
        assertSame(Range.NULL, sequence.eolEndRange(3));
        assertSame(Range.NULL, sequence.eolEndRange(4));
        assertSame(Range.NULL, sequence.eolEndRange(5));
        assertEquals(Range.of(5, 6), sequence.eolEndRange(6));
        assertSame(Range.NULL, sequence.eolEndRange(7));
        assertSame(Range.NULL, sequence.eolEndRange(8));
        assertSame(Range.NULL, sequence.eolEndRange(9));
        assertSame(Range.NULL, sequence.eolEndRange(10));
        assertEquals(Range.of(10, 11), sequence.eolEndRange(11));
    }

    @Test
    public void test_eolEndRange2() {
        String input = "\r1234\r6789\r";

        BasedSequence sequence = BasedSequence.of(input);
        assertSame(Range.NULL, sequence.eolEndRange(0));
        assertEquals(Range.of(0, 1), sequence.eolEndRange(1));
        assertSame(Range.NULL, sequence.eolEndRange(2));
        assertSame(Range.NULL, sequence.eolEndRange(3));
        assertSame(Range.NULL, sequence.eolEndRange(4));
        assertSame(Range.NULL, sequence.eolEndRange(5));
        assertEquals(Range.of(5, 6), sequence.eolEndRange(6));
        assertSame(Range.NULL, sequence.eolEndRange(7));
        assertSame(Range.NULL, sequence.eolEndRange(8));
        assertSame(Range.NULL, sequence.eolEndRange(9));
        assertSame(Range.NULL, sequence.eolEndRange(10));
        assertEquals(Range.of(10, 11), sequence.eolEndRange(11));
    }

    @Test
    public void test_eolEndRange3() {
        String input = "\r\n234\r\n789\r\n";

        BasedSequence sequence = BasedSequence.of(input);
        assertSame(Range.NULL, sequence.eolEndRange(0));
        assertSame(Range.NULL, sequence.eolEndRange(1));
        assertEquals(Range.of(0, 2), sequence.eolEndRange(2));
        assertSame(Range.NULL, sequence.eolEndRange(3));
        assertSame(Range.NULL, sequence.eolEndRange(4));
        assertSame(Range.NULL, sequence.eolEndRange(5));
        assertSame(Range.NULL, sequence.eolEndRange(6));
        assertEquals(Range.of(5, 7), sequence.eolEndRange(7));
        assertSame(Range.NULL, sequence.eolEndRange(8));
        assertSame(Range.NULL, sequence.eolEndRange(9));
        assertSame(Range.NULL, sequence.eolEndRange(10));
        assertSame(Range.NULL, sequence.eolEndRange(11));
        assertEquals(Range.of(10, 12), sequence.eolEndRange(12));
    }

    @Test
    public void test_eolEndLength1() {
        String input = "\n1234\n6789\n";

        BasedSequence sequence = BasedSequence.of(input);
        assertSame(0, sequence.eolEndLength(0));
        assertSame(0, sequence.eolEndLength(2));
        assertSame(0, sequence.eolEndLength(3));
        assertSame(0, sequence.eolEndLength(4));
        assertSame(0, sequence.eolEndLength(5));
        assertSame(0, sequence.eolEndLength(7));
        assertSame(0, sequence.eolEndLength(8));
        assertSame(0, sequence.eolEndLength(9));
        assertSame(0, sequence.eolEndLength(10));
        assertEquals(1, sequence.eolEndLength(1));
        assertEquals(1, sequence.eolEndLength(6));
        assertEquals(1, sequence.eolEndLength(11));
    }

    @Test
    public void test_eolEndLength2() {
        String input = "\r1234\r6789\r";

        BasedSequence sequence = BasedSequence.of(input);
        assertSame(0, sequence.eolEndLength(0));
        assertEquals(1, sequence.eolEndLength(1));
        assertSame(0, sequence.eolEndLength(2));
        assertSame(0, sequence.eolEndLength(3));
        assertSame(0, sequence.eolEndLength(4));
        assertSame(0, sequence.eolEndLength(5));
        assertEquals(1, sequence.eolEndLength(6));
        assertSame(0, sequence.eolEndLength(7));
        assertSame(0, sequence.eolEndLength(8));
        assertSame(0, sequence.eolEndLength(9));
        assertSame(0, sequence.eolEndLength(10));
        assertEquals(1, sequence.eolEndLength(11));
    }

    @Test
    public void test_eolEndLength3() {
        String input = "\r\n234\r\n789\r\n";

        BasedSequence sequence = BasedSequence.of(input);
        assertSame(0, sequence.eolEndLength(0));
        assertSame(0, sequence.eolEndLength(1));
        assertEquals(2, sequence.eolEndLength(2));
        assertSame(0, sequence.eolEndLength(3));
        assertSame(0, sequence.eolEndLength(4));
        assertSame(0, sequence.eolEndLength(5));
        assertSame(0, sequence.eolEndLength(6));
        assertEquals(2, sequence.eolEndLength(7));
        assertSame(0, sequence.eolEndLength(8));
        assertSame(0, sequence.eolEndLength(9));
        assertSame(0, sequence.eolEndLength(10));
        assertSame(0, sequence.eolEndLength(11));
        assertEquals(2, sequence.eolEndLength(12));
    }

    @Test
    public void test_eolStartRange1() {
        String input = "\n1234\n6789\n";

        BasedSequence sequence = BasedSequence.of(input);
        assertEquals(Range.of(0, 1), sequence.eolStartRange(0));
        assertSame(Range.NULL, sequence.eolStartRange(1));
        assertSame(Range.NULL, sequence.eolStartRange(2));
        assertSame(Range.NULL, sequence.eolStartRange(3));
        assertSame(Range.NULL, sequence.eolStartRange(4));
        assertEquals(Range.of(5, 6), sequence.eolStartRange(5));
        assertSame(Range.NULL, sequence.eolStartRange(6));
        assertSame(Range.NULL, sequence.eolStartRange(7));
        assertSame(Range.NULL, sequence.eolStartRange(8));
        assertSame(Range.NULL, sequence.eolStartRange(9));
        assertEquals(Range.of(10, 11), sequence.eolStartRange(10));
        assertSame(Range.NULL, sequence.eolStartRange(11));
    }

    @Test
    public void test_eolStartRange2() {
        String input = "\r1234\r6789\r";

        BasedSequence sequence = BasedSequence.of(input);
        assertEquals(Range.of(0, 1), sequence.eolStartRange(0));
        assertSame(Range.NULL, sequence.eolStartRange(1));
        assertSame(Range.NULL, sequence.eolStartRange(2));
        assertSame(Range.NULL, sequence.eolStartRange(3));
        assertSame(Range.NULL, sequence.eolStartRange(4));
        assertEquals(Range.of(5, 6), sequence.eolStartRange(5));
        assertSame(Range.NULL, sequence.eolStartRange(6));
        assertSame(Range.NULL, sequence.eolStartRange(7));
        assertSame(Range.NULL, sequence.eolStartRange(8));
        assertSame(Range.NULL, sequence.eolStartRange(9));
        assertEquals(Range.of(10, 11), sequence.eolStartRange(10));
        assertSame(Range.NULL, sequence.eolStartRange(11));
    }

    @Test
    public void test_eolStartRange3() {
        String input = "\r\n234\r\n789\r\n";

        BasedSequence sequence = BasedSequence.of(input);
        assertEquals(Range.of(0, 2), sequence.eolStartRange(0));
        assertSame(Range.NULL, sequence.eolStartRange(1));
        assertSame(Range.NULL, sequence.eolStartRange(2));
        assertSame(Range.NULL, sequence.eolStartRange(3));
        assertSame(Range.NULL, sequence.eolStartRange(4));
        assertEquals(Range.of(5, 7), sequence.eolStartRange(5));
        assertSame(Range.NULL, sequence.eolStartRange(6));
        assertSame(Range.NULL, sequence.eolStartRange(7));
        assertSame(Range.NULL, sequence.eolStartRange(8));
        assertSame(Range.NULL, sequence.eolStartRange(9));
        assertEquals(Range.of(10, 12), sequence.eolStartRange(10));
        assertSame(Range.NULL, sequence.eolStartRange(11));
        assertSame(Range.NULL, sequence.eolStartRange(12));
    }

    @Test
    public void test_eolStartLength1() {
        String input = "\n1234\n6789\n";

        BasedSequence sequence = BasedSequence.of(input);
        assertEquals(1, sequence.eolStartLength(0));
        assertSame(0, sequence.eolStartLength(1));
        assertSame(0, sequence.eolStartLength(2));
        assertSame(0, sequence.eolStartLength(3));
        assertSame(0, sequence.eolStartLength(4));
        assertEquals(1, sequence.eolStartLength(5));
        assertSame(0, sequence.eolStartLength(6));
        assertSame(0, sequence.eolStartLength(7));
        assertSame(0, sequence.eolStartLength(8));
        assertSame(0, sequence.eolStartLength(9));
        assertEquals(1, sequence.eolStartLength(10));
        assertSame(0, sequence.eolStartLength(11));
    }

    @Test
    public void test_eolStartLength2() {
        String input = "\r1234\r6789\r";

        BasedSequence sequence = BasedSequence.of(input);
        assertEquals(1, sequence.eolStartLength(0));
        assertSame(0, sequence.eolStartLength(1));
        assertSame(0, sequence.eolStartLength(2));
        assertSame(0, sequence.eolStartLength(3));
        assertSame(0, sequence.eolStartLength(4));
        assertEquals(1, sequence.eolStartLength(5));
        assertSame(0, sequence.eolStartLength(6));
        assertSame(0, sequence.eolStartLength(7));
        assertSame(0, sequence.eolStartLength(8));
        assertSame(0, sequence.eolStartLength(9));
        assertEquals(1, sequence.eolStartLength(10));
        assertSame(0, sequence.eolStartLength(11));
    }

    @Test
    public void test_eolStartLength3() {
        String input = "\r\n234\r\n789\r\n";

        BasedSequence sequence = BasedSequence.of(input);
        assertEquals(2, sequence.eolStartLength(0));
        assertSame(0, sequence.eolStartLength(1));
        assertSame(0, sequence.eolStartLength(2));
        assertSame(0, sequence.eolStartLength(3));
        assertSame(0, sequence.eolStartLength(4));
        assertEquals(2, sequence.eolStartLength(5));
        assertSame(0, sequence.eolStartLength(6));
        assertSame(0, sequence.eolStartLength(7));
        assertSame(0, sequence.eolStartLength(8));
        assertSame(0, sequence.eolStartLength(9));
        assertEquals(2, sequence.eolStartLength(10));
        assertSame(0, sequence.eolStartLength(11));
        assertSame(0, sequence.eolStartLength(12));
    }

    @Test
    public void test_trimToEndOfLine1() {
        String input = "\n234\n789\n";

        BasedSequence sequence = BasedSequence.of(input);
        assertEquals(Range.of(0, 0), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 0).getSourceRange());
        assertEquals(Range.of(0, 4), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 1).getSourceRange());
        assertEquals(Range.of(0, 4), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 2).getSourceRange());
        assertEquals(Range.of(0, 4), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 3).getSourceRange());
        assertEquals(Range.of(0, 4), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 4).getSourceRange());
        assertEquals(Range.of(0, 8), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 5).getSourceRange());
        assertEquals(Range.of(0, 8), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 6).getSourceRange());
        assertEquals(Range.of(0, 8), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 7).getSourceRange());
        assertEquals(Range.of(0, 8), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 8).getSourceRange());
        assertEquals(Range.of(0, 9), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 9).getSourceRange());
        assertEquals(Range.of(0, 9), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 10).getSourceRange());
        assertEquals(Range.of(0, 9), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 11).getSourceRange());
        assertEquals(Range.of(0, 9), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 12).getSourceRange());
        assertEquals(Range.of(0, 9), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 13).getSourceRange());
    }

    @Test
    public void test_trimToEndOfLine2() {
        String input = "\r234\r789\r";

        BasedSequence sequence = BasedSequence.of(input);
        assertEquals(Range.of(0, 0), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 0).getSourceRange());
        assertEquals(Range.of(0, 4), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 1).getSourceRange());
        assertEquals(Range.of(0, 4), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 2).getSourceRange());
        assertEquals(Range.of(0, 4), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 3).getSourceRange());
        assertEquals(Range.of(0, 4), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 4).getSourceRange());
        assertEquals(Range.of(0, 8), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 5).getSourceRange());
        assertEquals(Range.of(0, 8), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 6).getSourceRange());
        assertEquals(Range.of(0, 8), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 7).getSourceRange());
        assertEquals(Range.of(0, 8), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 8).getSourceRange());
        assertEquals(Range.of(0, 9), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 9).getSourceRange());
        assertEquals(Range.of(0, 9), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 10).getSourceRange());
        assertEquals(Range.of(0, 9), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 11).getSourceRange());
        assertEquals(Range.of(0, 9), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 12).getSourceRange());
        assertEquals(Range.of(0, 9), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 13).getSourceRange());
    }

    @Test
    public void test_trimToEndOfLine3() {
        String input = "\r\n234\r\n789\r\n";

        BasedSequence sequence = BasedSequence.of(input);
        assertEquals(Range.of(0, 0), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 0).getSourceRange());
        assertEquals(Range.of(0, 1), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 1).getSourceRange());
        assertEquals(Range.of(0, 5), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 2).getSourceRange());
        assertEquals(Range.of(0, 5), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 3).getSourceRange());
        assertEquals(Range.of(0, 5), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 4).getSourceRange());
        assertEquals(Range.of(0, 5), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 5).getSourceRange());
        assertEquals(Range.of(0, 6), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 6).getSourceRange());
        assertEquals(Range.of(0, 10), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 7).getSourceRange());
        assertEquals(Range.of(0, 10), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 8).getSourceRange());
        assertEquals(Range.of(0, 10), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 9).getSourceRange());
        assertEquals(Range.of(0, 10), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 10).getSourceRange());
        assertEquals(Range.of(0, 11), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 11).getSourceRange());
        assertEquals(Range.of(0, 12), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 12).getSourceRange());
        assertEquals(Range.of(0, 12), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 13).getSourceRange());
        assertEquals(Range.of(0, 12), sequence.trimToEndOfLine(BasedSequence.EOL_CHARS, false, 14).getSourceRange());
    }
}