package com.rill;

import com.rill.constants.Constants;
import com.rill.util.GenerateExpressionUtil;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

;

/**
 * @author ZhangPeng
 * @date 2017/11/16
 */
public class GenerateExpressionUtilTest {

    @Test
    public void testRange10() throws Exception {
        String path = GenerateExpressionUtilTest.class.getClassLoader().getResource(Constants.TEMPLATEPATH_100).getPath();
        GenerateExpressionUtil.exportWithTemplate(path,Constants.PAGENUM_10,Constants.MAXVALUE_10,Constants.OPERATOR_PLUSMINUS,Constants.EXPR_TYPE_NORMAL);
    }


    @Test
    public void testRange20() throws Exception {
        String path = GenerateExpressionUtilTest.class.getClassLoader().getResource(Constants.TEMPLATEPATH_100).getPath();
        GenerateExpressionUtil.exportWithTemplate(path,Constants.PAGENUM_10,Constants.MAXVALUE_20,Constants.OPERATOR_PLUSMINUS,Constants.EXPR_TYPE_NORMAL);
    }

    @Test
    public void testRange20LeftBrackets() throws Exception {
        String path = GenerateExpressionUtilTest.class.getClassLoader().getResource(Constants.TEMPLATEPATH_100).getPath();
        GenerateExpressionUtil.exportWithTemplate(path,Constants.PAGENUM_10,Constants.MAXVALUE_20,Constants.OPERATOR_PLUSMINUS,Constants.EXPR_TYPE_LEFT_BRACKETS);
    }

    @Test
    public void testRange20RightBrackets() throws Exception {
        String path = GenerateExpressionUtilTest.class.getClassLoader().getResource(Constants.TEMPLATEPATH_100).getPath();
        GenerateExpressionUtil.exportWithTemplate(path,Constants.PAGENUM_10,Constants.MAXVALUE_20,Constants.OPERATOR_PLUSMINUS,Constants.EXPR_TYPE_RIGHT_BRACKETS);
    }

    @Test
    public void test(){
        ExpressionParser parser =new SpelExpressionParser();
        Expression exp = parser.parseExpression("1+(2)");
        int result = (int) exp.getValue();
        System.out.println(result);
        String pattern = "\\([^)]*\\)";
        String str = "(2+3)+4";
        System.out.println(str.replaceAll(pattern,"()"));
    }
}