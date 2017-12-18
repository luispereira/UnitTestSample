package com.lpereira.uts.unittestsample.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lpereira on 17/12/2017.
 */
public class JavaTextUtilsTest {
    @Test
    public void isEmpty() throws Exception {
        //GIVEN
        String emptyValue = "";
        String nullValue = null;
        String containsValue = "something";
        //WHEN
        boolean isEmpty = JavaTextUtils.isEmpty(emptyValue);
        boolean isNotEmpty = JavaTextUtils.isEmpty(containsValue);
        boolean isNullEmpty = JavaTextUtils.isEmpty(nullValue);
        //THEN
        Assert.assertTrue(isEmpty);
        Assert.assertFalse(isNotEmpty);
        Assert.assertTrue(isNullEmpty);
    }

    @Test
    public void equals() throws Exception {
        //GIVEN
        String valueOne = "something";
        String valueTwo = "something";
        String differentValueTwo = "something2";
        String differentValueOne = "empty";
        String nullValue = null;
        String emptyValue = "";
        //WHEN
        boolean isEquals = JavaTextUtils.equals(valueOne, valueTwo);
        boolean isNotEqualsTwo = JavaTextUtils.equals(valueOne, differentValueTwo);
        boolean isNotEqualsOne = JavaTextUtils.equals(differentValueOne, valueTwo);
        boolean isNullEmptyEquals = JavaTextUtils.equals(nullValue, emptyValue);
        boolean isNullNotEquals = JavaTextUtils.equals(nullValue, valueOne);
        boolean isNullEquals = JavaTextUtils.equals(nullValue, nullValue);
        //THEN
        Assert.assertTrue(isEquals);
        Assert.assertFalse(isNotEqualsTwo);
        Assert.assertFalse(isNotEqualsOne);
        Assert.assertFalse(isNullNotEquals);
        Assert.assertFalse(isNullEmptyEquals);
        Assert.assertFalse(isNullEquals);
    }

}