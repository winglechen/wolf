package com.onedot.win.common.model.type.string;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * com.onedot.win.common.model.type.string
 *
 * @author Wingle
 * @since 2019/12/20 5:44 下午
 **/
public class TagTest {

    @Test
    public void add() {
        Tag tag = new Tag();
        tag.add("php").add("java").add("js");

        assertTrue("tag add fail", tag.contains("php"));
        assertTrue("tag add fail", tag.contains("java"));
        assertTrue("tag add fail", tag.contains("js"));

        String expected = "java,js,php";
        assertEquals("tag join fail", expected, tag.toString());
    }

    @Test
    public void contains() {
        String tagString = "php, java, js, php";
        Tag tag = new Tag(tagString);

        assertTrue("tag contains fail", tag.contains("php"));
        assertTrue("tag contains fail", tag.contains("java"));
        assertTrue("tag contains fail", tag.contains("js"));

        String expected = "java,js,php";
        assertEquals("tag join fail", expected, tag.toString());

    }

    @Test
    public void remove() {
        String tagString = "php, java, js, php";
        Tag tag = new Tag(tagString);

        assertTrue("tag remove fail", tag.contains("php"));
        assertTrue("tag remove fail", tag.contains("java"));

        tag.remove("js");
        assertFalse("tag remove fail", tag.contains("js"));

        String expected = "java,php";
        assertEquals("tag join fail", expected, tag.toString());

    }


}