package com.rill;

;
import com.rill.constants.Constants;
import com.rill.util.GenerateExpressionUtil;
import org.junit.Test;

/**
 * @author ZhangPeng
 * @date 2017/11/16
 */
public class GenerateExpressionUtilTest {

    @Test
    public void test() throws Exception {
        GenerateExpressionUtil.exportWithTemplate(Constants.PAGENUM_10,Constants.MAXVALUE_10,Constants.OPERATOR_PLUSMINUS);
    }
}