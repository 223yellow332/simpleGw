package com.calmdown.simpleGw.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommonUtil {

    public static String getTrxId(String mobile){
        String dateStr = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        return "TEST_" + mobile + dateStr + getRanStr();
    }

    public static String getRanStr() {
        int ranNum = (int) (Math.random() * 1000000);
        String tmpNum = Integer.toString(ranNum);
        int len = tmpNum.length();

        for(int i = len; i < 6; i++) {
            tmpNum += "0";
        }

        return tmpNum;
    }
}
