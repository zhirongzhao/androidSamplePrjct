package com.example;

import java.util.Date;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class LotteryDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1003, "com.sample.androidsampleprjct.util.db");

        addLottery(schema);
        addSSQ(schema);
        addSSQRemote(schema);
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
    static void addSSQ(Schema schema){
        Entity ssq = schema.addEntity("SSQ");
        ssq.setTableName("LT_SSQ");
        ssq.addIdProperty();
        ssq.addIntProperty("red1").notNull();
        ssq.addIntProperty("red2").notNull();
        ssq.addIntProperty("red3").notNull();
        ssq.addIntProperty("red4").notNull();
        ssq.addIntProperty("red5").notNull();
        ssq.addIntProperty("red6").notNull();
        ssq.addIntProperty("red7").notNull();
        ssq.addIntProperty("red8").notNull();
        ssq.addIntProperty("blu").notNull();
        ssq.addStringProperty("expect").notNull();
        ssq.addStringProperty("regDate");
        ssq.addStringProperty("isWin");

    }
    static void addSSQRemote(Schema schema){
       // expect":"2015063","openCode":"01,04,06,34,35+02,04","openTime":"2015-06-03 20:38:00","openTimeStamp":"1433335080000
        Entity ssqRemoteDB = schema.addEntity("SSQExtVO");
        ssqRemoteDB.setTableName("LT_SSQ_REMOTE");
        ssqRemoteDB.addIdProperty();
        ssqRemoteDB.addStringProperty("expect");
        ssqRemoteDB.addStringProperty("openCode");
        ssqRemoteDB.addStringProperty("openTime");
        ssqRemoteDB.addStringProperty("openTimeStamp");
        ssqRemoteDB.addStringProperty("flag");
        ssqRemoteDB.addDateProperty("regDtm");
    }

}
