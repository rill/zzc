package com.rill.model;

import com.github.crab2died.annotation.ExcelField;

import java.io.Serializable;

/**
 * @author ZhangPeng
 * @date 2017/11/14
 */
public class MentalArithmeticModel implements Serializable {
    @ExcelField(title = "", order = 1)
    private String cell1;
    @ExcelField(title = "", order = 2)
    private String cell2;
    @ExcelField(title = "", order = 3)
    private String cell3;
    @ExcelField(title = "", order = 4)
    private String cell4;
    @ExcelField(title = "", order = 5)
    private String cell5;

    public MentalArithmeticModel() {
    }

    public MentalArithmeticModel(String cell1, String cell2, String cell3, String cell4, String cell5) {
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.cell3 = cell3;
        this.cell4 = cell4;
        this.cell5 = cell5;
    }

    public String getCell1() {
        return cell1;
    }

    public void setCell1(String cell1) {
        this.cell1 = cell1;
    }

    public String getCell2() {
        return cell2;
    }

    public void setCell2(String cell2) {
        this.cell2 = cell2;
    }

    public String getCell3() {
        return cell3;
    }

    public void setCell3(String cell3) {
        this.cell3 = cell3;
    }

    public String getCell4() {
        return cell4;
    }

    public void setCell4(String cell4) {
        this.cell4 = cell4;
    }

    public String getCell5() {
        return cell5;
    }

    public void setCell5(String cell5) {
        this.cell5 = cell5;
    }
}
