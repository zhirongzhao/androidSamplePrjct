package com.example;

import java.util.Date;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class LotteryDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "com.sample.androidsampleprjct.util.db");

        addLottery(schema);
//        addCustomerOrder(schema);
        //System.out.println();
        new DaoGenerator().generateAll(schema, "E:/AS_2_BATE5/androidSamplePrjct/app/src/main/java");
    }

    static void addLottery(Schema schema) {
        Entity lottery = schema.addEntity("Lottery");
        lottery.setTableName("LT_LOTTERY");
        lottery.addIdProperty();
        lottery.addStringProperty("lotteryCode").notNull();
        lottery.addStringProperty("expect").notNull();
        lottery.addStringProperty("candidateCode");
        lottery.addStringProperty("candidateCode2");
        lottery.addDateProperty("regDate");
        lottery.addStringProperty("isWin");
    }

}
