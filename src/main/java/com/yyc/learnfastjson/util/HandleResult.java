package com.yyc.learnfastjson.util;
/**
 * @Author yinyichen
 * @Date 8/24/2022 3:21 PM
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yinyichen
 */
public class HandleResult {
    public static String handleResult(String DatewhithoutSort) {
        //传递参数名：DatewhithoutSort
//        Arrays.stream(DatewhithoutSort.split("\\["))
//                .forEach(str -> str.replace("\\]", ""));
//        List<String> spiltArr = new ArrayList<>();
//        for (int i = 0; i < split.length; i++) {
//            String replace = split[i].replace("\\]", "");
//            if (replace.length() >= 2) {
//                StringBuffer stringBuffer = new StringBuffer(replace).deleteCharAt(replace.length() - 1).insert(0, "[");
//                String fn = stringBuffer.toString();
//                spiltArr.add(fn);
//            }
//        }

        String[] split = DatewhithoutSort.split("\\[");
        List<String> spiltArr = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String replace = split[i].replace("\\]", "");
            if (replace.length() >= 2) {
                StringBuffer stringBuffer = new StringBuffer(replace).deleteCharAt(replace.length() - 1).insert(0, "[");
                String fn = stringBuffer.toString();
                spiltArr.add(fn);
            }
        }

        Collections.sort(spiltArr);
        String s = spiltArr.get(spiltArr.size() - 1);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(spiltArr);
        arrayList.add(0, s);
        arrayList.remove(arrayList.size() - 1);
        spiltArr.clear();
        spiltArr.addAll(arrayList);
        
        return spiltArr.toString();
    }

    public static void main(String[] args) {
        System.out.println(handleResult("[[\"日期\",\"订单数\",\"SUK数量\",\"库存数\"],[\"02-02\",0,0,0],[\"02-01\",3,3,107],[\"02-03\",1,1,2]]"));
    }
}
