package com.rill.util;

import com.github.crab2died.ExcelUtils;
import com.rill.constants.Constants;
import com.rill.model.MentalArithmeticModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangPeng
 * @date 2017/11/14
 */
public class GenerateExpressionUtil {

    /**
     * 生成指定页数的指定数字以内的口算题
     * @param pageNum 页数
     * @param maxValue 最大值
     * @param operator 运算符
     * @throws Exception
     */
    public static void exportWithTemplate(int pageNum, int maxValue, String operator) throws Exception {
        List<MentalArithmeticModel> rowList = new ArrayList<>();
        for(int m=0;m<pageNum;m++){
            for (int i = 0; i < 3;i++) {
                MentalArithmeticModel mentalArithmeticModel = new MentalArithmeticModel("","","","","");
                rowList.add(mentalArithmeticModel);
            }
            for (int i = 0; i < Constants.ROWNUM; i++) {
                rowList.add(initModel(maxValue,operator));
            }
        }
        String targetPath = LocalDate.now().toString()+".xlsx";
        ExcelUtils.getInstance().exportObjects2Excel(Constants.TEMPLATEPATH_100, 0, rowList, null, MentalArithmeticModel.class, false, targetPath);
    }

    /**
     * 生成一行的数据
     * @param maxValue 最大值
     * @param operator 运算符
     * @return
     */
    public static MentalArithmeticModel initModel(int maxValue,String operator){
        MentalArithmeticModel mentalArithmeticModel = new MentalArithmeticModel();
        mentalArithmeticModel.setCell1(generateExpr(maxValue,operator));
        mentalArithmeticModel.setCell2(generateExpr(maxValue,operator));
        mentalArithmeticModel.setCell3(generateExpr(maxValue,operator));
        mentalArithmeticModel.setCell4(generateExpr(maxValue,operator));
        mentalArithmeticModel.setCell5(generateExpr(maxValue,operator));
        return mentalArithmeticModel;
    }

    /**
     * 生成指定数字以内的表达式
     * @param maxValue 最大值
     * @param operator 运算符
     * @return
     */
    public static String generateExpr(int maxValue,String operator) {
        String expr;
        int start = RandomUtils.nextInt(1,maxValue+1);
        int end = RandomUtils.nextInt(1,maxValue+1);
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        String randomOperator = RandomStringUtils.random(1,operator);
        sb.append(randomOperator);
        sb.append(end);
        ExpressionParser parser =new SpelExpressionParser();
        Expression exp = parser.parseExpression(sb.toString());
        int result = (int) exp.getValue();
        if(result > maxValue || result < 0){
           return generateExpr(maxValue,operator);
        }else{
            expr = exp.getExpressionString() + "=";
        }
        return expr;
    }

    public static void main(String[] ars) throws Exception {
        exportWithTemplate(Constants.PAGENUM_10,Constants.MAXVALUE_10,Constants.OPERATOR_PLUSMINUS);
    }
}
