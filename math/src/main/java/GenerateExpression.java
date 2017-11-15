import com.github.crab2died.ExcelUtils;
import com.rill.constants.Constants;
import com.rill.model.MentalArithmeticModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangPeng
 * @date 2017/11/14
 */
public class GenerateExpression {

    public static void main(String[] ars) throws Exception {
        //exportWithooutTemplate();
        exportWithTemplate();
    }

    public static void exportWithTemplate() throws Exception {
        List<MentalArithmeticModel> rowList = new ArrayList<>();
        for (int i = 0; i < Constants.ROWNUM; i++) {
            MentalArithmeticModel mentalArithmeticModel = new MentalArithmeticModel(generate(),generate(),generate(),generate(),generate());
            rowList.add(mentalArithmeticModel);
        }
        Map<String, String> headMap = new HashMap<>();
        headMap.put("title", "口算一百道");
        headMap.put("info", "姓名：_______  时间：______   做对了_____题（共100题）");
        //ExcelUtils.getInstance().exportObjects2Excel(Constants.TEMPLATEPATH,rowList, MentalArithmeticModel.class,"d:/exportWithTemplate.xlsx");
        ExcelUtils.getInstance().exportObjects2Excel(Constants.TEMPLATEPATH, 0, rowList, headMap, MentalArithmeticModel.class, false, "d:/exportWithTemplate.xlsx");
    }


    public static void exportWithooutTemplate() throws Exception {
        List<List<String>> rowList = new ArrayList<>();
        for (int i = 0; i < Constants.ROWNUM; i++) {
            List<String> columnList = new ArrayList<>();
            for (int j = 0; j < Constants.COLUMNNUM; j++) {
                String expr = generate();
                columnList.add(expr);
            }
            rowList.add(columnList);
        }
        ExcelUtils.getInstance().exportObjects2Excel(rowList,"d:/exportWithooutTemplate.xlsx");
    }

    public static String generate() {
        String expr = "";
        int start = RandomUtils.nextInt(1,Constants.MAXVALUE_10+1);
        int end = RandomUtils.nextInt(1,Constants.MAXVALUE_10+1);
        StringBuilder sb = new StringBuilder();
        sb.append(start);
        String operator = RandomStringUtils.random(1,Constants.OPERATOR_PLUSMINUS);
        sb.append(operator);
        sb.append(end);
        ExpressionParser parser =new SpelExpressionParser();
        Expression exp = parser.parseExpression(sb.toString());
        int result = (int) exp.getValue();
        if(result > Constants.MAXVALUE_10 || result < 0){
           return generate();
        }else{
            expr = exp.getExpressionString() + "=";
        }
        return expr;
    }


}
