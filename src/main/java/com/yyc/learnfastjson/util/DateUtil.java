package com.yyc.learnfastjson.util;
/**
 * @Author yinyichen
 * @Date 8/23/2022 5:44 PM
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author yinyichen
 */
public class DateUtil {
    /**
     * 切割時間段
     *
     * @param dateType 交易類型 M/D/H/N -->每月/每天/每小時/每分鐘
     * @param start    yyyy-MM-dd HH:mm:ss
     * @param end      yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static List<String> cutDate(String dateType, String start, String end) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dBegin = sdf.parse(start);
            Date dEnd = sdf.parse(end);
            return findDates(dateType, dBegin, dEnd);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static List<String> findDates(String dateType, Date dBegin, Date dEnd) throws Exception {
        List<String> listDate = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        listDate.add(sdf.format(calBegin.getTime()));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);
        while (calEnd.after(calBegin)) {
            switch (dateType) {
                case "M":
                    calBegin.add(Calendar.MONTH, 1);
                    break;
                case "D":
                    calBegin.add(Calendar.DAY_OF_YEAR, 1);
                    break;
                case "H":
                    calBegin.add(Calendar.HOUR, 1);
                    break;
                default:
                    break;
            }
            if (calEnd.after(calBegin)) {
                listDate.add(sdf.format(calBegin.getTime()));
            } else {
                listDate.add(sdf.format(calEnd.getTime()));
            }
        }
        return listDate;
    }

    /**
     * description 通过入参时间得到往后推一天的时间来按天切片
     * @param str 代表需要增加一天的时间
     * @return
     */
    public static String addDayForData(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBegin;
        try {
            dBegin = sdf.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
//        calBegin.add(Calendar.DAY_OF_YEAR, 1);
        String result = sdf.format(calBegin.getTime());
//        System.out.println(result);
        return result;
    }

}