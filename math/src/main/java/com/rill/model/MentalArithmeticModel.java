package com.rill.model;

import java.io.Serializable;

/**
 * @author ZhangPeng
 * @date 2017/11/14
 */
public class MentalArithmeticModel implements Serializable {
    private String cell1;
    private String cell2;
    private String cell3;
    private String cell4;
    private String cell5;

    public MentalArithmeticModel(String cell1, String cell2, String cell3, String cell4, String cell5) {
        this.cell1 = cell1;
        this.cell2 = cell2;
        this.cell3 = cell3;
        this.cell4 = cell4;
        this.cell5 = cell5;
    }
}
