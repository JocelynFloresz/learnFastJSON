package com.yyc.learnfastjson.util;
/**
 * @Author yinyichen
 * @Date 8/23/2022 4:06 PM
 */

import com.alibaba.fastjson.JSONPath;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import static com.yyc.learnfastjson.controller.HtttpTest.END_OF_DAY;

/**
 * @author yinyichen
 */
public class QueryFrmLossByTimeFragment {
    public String queryFromLossByDayToTemplate(String startTime, String endTime) {
        String eT = endTime + END_OF_DAY;

        WebClient webClient = WebClient.builder()
                .defaultHeader("authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxNTQ5NjMzOTIwNjkyNTg4NTQ1IiwidG9rZW5JZCI6IjA3YjZmMDc3LWNiNzUtNDhjYi1hODQ3LTQyZGVlZDJjNTIzNCIsImFtciI6WyJwd2QiXSwiaXNzIjoiaHR0cHM6Ly9reWxpbi51YXQueHN5eHNjLmNuIiwidmVyc2lvbiI6IjIuMCIsImVuY3J5cHRBdHRyIjoicHQ5dVo4OW1uVkJRd2lqaFlmSnBQdWNSX3ptLU8tazZaaFZBbWNXQnM0SlZ6Tm1kQWpWWk5YRzRoTkRNci00bnFEWHBrWHpIOFNyZjRPWmkxMm9zdkEiLCJjbGllbnRfaWQiOiJ4c3l4LXlleWluZy1neGMtd2ViIiwiYXVkIjpbInhzeXgtd21zLXdlYi1ib3NzIiwiaHR0cHM6Ly9reWxpbi51YXQueHN5eHNjLmNuL3Jlc291cmNlcyIsInhzeXgteWV5aW5nIiwieHN5eC15ZXlpbmctZ3hjLXdlYiIsInhzeXgtenhjLWFwcCJdLCJuYmYiOjE2NjExNTIxMDQsImlkcCI6ImxvY2FsIiwic2NvcGUiOlsieHN5eC13bXMtd2ViLWJvc3MiLCJ4c3l4LXlleWluZyIsInhzeXgtenhjLWFwcCJdLCJhdXRoX3RpbWUiOjE2NjExNTIxMDQsIm5hbWUiOiLpmLTlpZXovrAiLCJleHAiOjE2NjIwMTYxMDQsImlhdCI6MTY2MTE1MjEwNCwianRpIjoiNWZjZjFjYWItYzE0NC00MzA4LWI1N2UtMGY1ODJiZjNkMDMzIn0.dovo76Vg9FzVF4XsJPnZFWtzfT-cyKa1UBnP3n7x-ZhTcsT_tOcu_GAUoaeMHID6jVFx9ocLs4-r-dQmPntARLMlSooJ-PZhSV8jA3_vLxTJgg7csVZEy14u5nGEWbNWVgCZtE5icsFVffNbVx79tJmrJD5qJmEWb45T16QwoftTHJa3GwV_1n5PIpqh9TPDzCKmMAMHFmm8ZzjgF3Xvn-H_1vONzHE-XfUDKwl3panSFQ_gq0xYnY5Sxb9FhRCAwJvPf-SQm4zsSNCRJ6w6XUeHKVNaQibV0AgMRkaUbuEQ7FwOP80x7cfe3PgwV676MjoXXB8po22wUVzEN-L_Dw")
                .build();
        WebClient.RequestBodySpec uri = webClient.post().uri("http://rivers-uat.xsyxsc.cn/frmlossapi/noAuth/frmloss/GetFrmLossDetailByTime");

        System.out.println(startTime + " " + eT);
        Map<String, Object> requestMap = new HashMap<>(16);
        requestMap.put("beginReportTime", startTime);
        requestMap.put("endReportTime", eT);
        requestMap.put("pageIndex", "0");
        requestMap.put("pageSize", "100");

        StringBuffer result = new StringBuffer();
        Mono<String> mono = uri.bodyValue(requestMap).retrieve().bodyToMono(String.class);
        mono.subscribe(i -> {
                Object frmLossSkuCount = JSONPath.read(i, "$.data.data[*].frmLossSkuCount.sum()");
                Object frmLossTotalNum = JSONPath.read(i, "$.data.data[*].frmLossTotalNum.sum()");
                Object total = JSONPath.read(i, "$.data.total");
                i = ",[" +
                    "\"" + endTime.split("-", 2)[1] + "\"," +
                    total + "," +
                    frmLossSkuCount + ","
                    + frmLossTotalNum + "]";
                    System.out.println(i);
                    result.append(i);
            },
            error -> System.err.println("Error " + error),
            () -> System.out.println("Done"));

        String rs = String.valueOf(result);
        System.out.println(rs);
        return rs;
    }


    public static void main(String[] args) {
    }
}
