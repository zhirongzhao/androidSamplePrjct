package com.sample.androidsampleprjct.vo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "LT_SSQ".
 */
public class SSQ {

    private Long id;
    private int red1;
    private int red2;
    private int red3;
    private int red4;
    private int red5;
    private int red6;
    private int red7;
    private int red8;
    private int blu;
    /** Not-null value. */
    private String expect;
    private String regDate;
    private String isWin;

    public SSQ() {
    }

    public SSQ(Long id) {
        this.id = id;
    }

    public SSQ(Long id, int red1, int red2, int red3, int red4, int red5, int red6, int red7, int red8, int blu, String expect, String regDate, String isWin) {
        this.id = id;
        this.red1 = red1;
        this.red2 = red2;
        this.red3 = red3;
        this.red4 = red4;
        this.red5 = red5;
        this.red6 = red6;
        this.red7 = red7;
        this.red8 = red8;
        this.blu = blu;
        this.expect = expect;
        this.regDate = regDate;
        this.isWin = isWin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRed1() {
        return red1;
    }

    public void setRed1(int red1) {
        this.red1 = red1;
    }

    public int getRed2() {
        return red2;
    }

    public void setRed2(int red2) {
        this.red2 = red2;
    }

    public int getRed3() {
        return red3;
    }

    public void setRed3(int red3) {
        this.red3 = red3;
    }

    public int getRed4() {
        return red4;
    }

    public void setRed4(int red4) {
        this.red4 = red4;
    }

    public int getRed5() {
        return red5;
    }

    public void setRed5(int red5) {
        this.red5 = red5;
    }

    public int getRed6() {
        return red6;
    }

    public void setRed6(int red6) {
        this.red6 = red6;
    }

    public int getRed7() {
        return red7;
    }

    public void setRed7(int red7) {
        this.red7 = red7;
    }

    public int getRed8() {
        return red8;
    }

    public void setRed8(int red8) {
        this.red8 = red8;
    }

    public int getBlu() {
        return blu;
    }

    public void setBlu(int blu) {
        this.blu = blu;
    }

    /** Not-null value. */
    public String getExpect() {
        return expect;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getIsWin() {
        return isWin;
    }

    public void setIsWin(String isWin) {
        this.isWin = isWin;
    }

}
