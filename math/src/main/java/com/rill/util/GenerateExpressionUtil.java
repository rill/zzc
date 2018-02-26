package com.rill.util;

import com.github.crab2died.ExcelUtils;
import com.rill.constants.Constants;
import com.rill.enums.ExprTypeEnum;
import com.rill.model.MentalArithmeticModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public static void exportWithTemplate(String templatePath,int pageNum, int maxValue, String operator,ExprTypeEnum exprType) throws Exception {
        List<MentalArithmeticModel> rowList = new ArrayList<>();
        for(int m=0;m<pageNum;m++){
            for (int i = 0; i < 3;i++) {
                MentalArithmeticModel mentalArithmeticModel = new MentalArithmeticModel("","","","","");
                rowList.add(mentalArithmeticModel);
            }
            for (int i = 0; i < Constants.ROWNUM; i++) {
                rowList.add(initModel(maxValue,operator,exprType));
            }
        }
        String targetPath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+exprType.getName()+".xlsx";
        ExcelUtils.getInstance().exportObjects2Excel(templatePath, 0, rowList, null, MentalArithmeticModel.class, false, targetPath);
    }

    /**
     * 生成一行的数据
     * @param maxValue 最大值
     * @param operator 运算符
     * @return
     */
    public static MentalArithmeticModel initModel(int maxValue,String operator,ExprTypeEnum exprType){
        MentalArithmeticModel mentalArithmeticModel = new MentalArithmeticModel();
        if(exprType.getType() == ExprTypeEnum.NORMAL.getType()){
            mentalArithmeticModel.setCell1(generateExpr(maxValue,operator));
            mentalArithmeticModel.setCell2(generateExpr(maxValue,operator));
            mentalArithmeticModel.setCell3(generateExpr(maxValue,operator));
            mentalArithmeticModel.setCell4(generateExpr(maxValue,operator));
            mentalArithmeticModel.setCell5(generateExpr(maxValue,operator));
        }else if(exprType.getType() == ExprTypeEnum.LEFT_BRACKETS.getType()){
            mentalArithmeticModel.setCell1(generateExprWithLeftBrackets(maxValue,operator));
            mentalArithmeticModel.setCell2(generateExprWithLeftBrackets(maxValue,operator));
            mentalArithmeticModel.setCell3(generateExprWithLeftBrackets(maxValue,operator));
            mentalArithmeticModel.setCell4(generateExprWithLeftBrackets(maxValue,operator));
            mentalArithmeticModel.setCell5(generateExprWithLeftBrackets(maxValue,operator));
        }else if(exprType.getType() == ExprTypeEnum.RIGHT_BRACKETS.getType()){
            mentalArithmeticModel.setCell1(generateExprWithRightBrackets(maxValue,operator));
            mentalArithmeticModel.setCell2(generateExprWithRightBrackets(maxValue,operator));
            mentalArithmeticModel.setCell3(generateExprWithRightBrackets(maxValue,operator));
            mentalArithmeticModel.setCell4(generateExprWithRightBrackets(maxValue,operator));
            mentalArithmeticModel.setCell5(generateExprWithRightBrackets(maxValue,operator));
        }
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
        sb.append(" ").append(randomOperator).append(" ");
        sb.append(end);
        ExpressionParser parser =new SpelExpressionParser();
        Expression exp = parser.parseExpression(sb.toString());
        int result = (int) exp.getValue();
        if(result > maxValue || result < 0){
            return generateExpr(maxValue,operator);
        }else{
            expr = exp.getExpressionString() + " = ";
        }
        return expr;
    }

    /**
     * 生成第一个数是括号的表达式
     * @param maxValue
     * @param operator
     * @return
     */
    public static String generateExprWithLeftBrackets(int maxValue,String operator) {
        String expr;
        String start = "("+RandomUtils.nextInt(1,maxValue+1)+")";
        int end = RandomUtils.nextInt(1,maxValue+1);
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        String randomOperator = RandomStringUtils.random(1,operator);
        sb.append(" ").append(randomOperator).append(" ");
        sb.append(end);
        ExpressionParser parser =new SpelExpressionParser();
        Expression exp = parser.parseExpression(sb.toString());
        int result = (int) exp.getValue();
        if(result > maxValue || result < 0){
            return generateExprWithLeftBrackets(maxValue,operator);
        }else{

            expr = exp.getExpressionString() + " = " + result;
            expr = expr.replaceAll(Constants.PATTERN_BRACKETS,"(   )");
        }
        return expr;
    }

    /**
     * 生成第二个数是括号的表达式
     * @param maxValue
     * @param operator
     * @return
     */
    public static String generateExprWithRightBrackets(int maxValue,String operator) {
        String expr;
        int start = RandomUtils.nextInt(1,maxValue+1);
        String end = "("+RandomUtils.nextInt(1,maxValue+1)+")";
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        String randomOperator = RandomStringUtils.random(1,operator);
        sb.append(" ").append(randomOperator).append(" ");
        sb.append(end);
        ExpressionParser parser =new SpelExpressionParser();
        Expression exp = parser.parseExpression(sb.toString());
        int result = (int) exp.getValue();
        if(result > maxValue || result < 0){
            return generateExprWithRightBrackets(maxValue,operator);
        }else{

            expr = exp.getExpressionString() + " = " + result;
            expr = expr.replaceAll(Constants.PATTERN_BRACKETS,"(   )");
        }
        return expr;
    }
}
