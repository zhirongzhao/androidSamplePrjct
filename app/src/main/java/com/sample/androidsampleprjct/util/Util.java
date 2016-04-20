package com.sample.androidsampleprjct.util;

import com.sample.androidsampleprjct.vo.SSQ;
import com.sample.androidsampleprjct.vo.SSQExtVO;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by samsung on 2016/4/11.
 */
public class Util {

    public static boolean compareSSQ(SSQ ssqVO , String ssqStr){
        if(ssqStr.split("\\+")[1].equals(ssqVO.getBlu())){
            return true;
        }
        int compareRed = 0;
        String redBull = ssqStr.split("\\+")[0];
        String[] reds = redBull.split(",");
        if(reds[0].equals(ssqVO.getRed1())){
            compareRed++;
        }
        if(reds[1].equals(ssqVO.getRed2())){
            compareRed++;
        }
        if(reds[2].equals(ssqVO.getRed3())){
            compareRed++;
        }
        if(reds[3].equals(ssqVO.getRed4())){
            compareRed++;
        }
        if(reds[4].equals(ssqVO.getRed5())){
            compareRed++;
        }
        if(reds[5].equals(ssqVO.getRed6())){
            compareRed++;
        }
        if (compareRed>=4){
            return true;
        }else {
            return false;
        }

    }

    public static String getNow(){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(now);
    }
}
