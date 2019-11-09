package ext.antlr4.expr.common;

import org.junit.Test;

import junit.framework.TestCase;

public class NumberUtilTest {

    @Test
    public void isDictIdTest(){
        TestCase.assertEquals(true, NumberUtil.isDictId("_1000.1001_"));
        TestCase.assertEquals(false, NumberUtil.isDictId("_10001001_"));
        TestCase.assertEquals(false, NumberUtil.isDictId("_1000.1001"));
    }
}
