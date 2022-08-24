package com.yyc.learnfastjson.controller;
/**
 * @Author yinyichen
 * @Date 8/23/2022 9:26 AM
 */

import com.yyc.learnfastjson.util.DateUtil;
import com.yyc.learnfastjson.util.QueryFrmLossByTimeFragment;

import java.util.*;
import static com.yyc.learnfastjson.util.DateUtil.addDayForData;
import static com.yyc.learnfastjson.util.HandleResult.handleResult;

/**
 * @author yinyichen
 */
public class HtttpTest {
    public final static String BEGIN_OF_DAY = " 00:00:00";
    public final static String END_OF_DAY = " 23:59:59";

    public static String queryFromLossCount(String startTime, String endTime, String dateType) throws InterruptedException {
        String sT = startTime + BEGIN_OF_DAY;
        String eT = endTime + END_OF_DAY;
        QueryFrmLossByTimeFragment queryFrmLossByTimeFragment = new QueryFrmLossByTimeFragment();
        StringBuffer result = new StringBuffer(300).append("[[\"日期\",\"订单数\",\"SUK数量\",\"库存数\"]");
//        System.out.println(result);
        switch (dateType) {
            case "Y":

                break;
            case "M":
                List<String> monthList = DateUtil.cutDate("M", sT, eT);
                System.out.println(monthList);
                monthList.parallelStream()
                        .limit(monthList.toArray().length-1)
                        .forEach(str -> result.append(queryFrmLossByTimeFragment.queryFromLossByDayToTemplate(str, addDayForData(str))));
                result.append("]");
                System.out.println("==============================================================================" +
                        "=========================================================================================");
                System.out.println(handleResult(String.valueOf(result)));
                break;
            case "D":
                List<String> dayList = DateUtil.cutDate("D", sT, eT);
//                dayList.stream().limit(dayList.toArray().length-1).forEach(str -> System.out.println(str + "  " + addDayForData(str)));
                dayList.parallelStream()
                        .limit(dayList.toArray().length-1)
                        .forEach(str -> result.append(queryFrmLossByTimeFragment.queryFromLossByDayToTemplate(str, addDayForData(str))));
                System.out.println(handleResult(String.valueOf(result.append("]"))));
                break;
            default:
                break;
        }

        return "";
    }

    public static void main(String[] args) throws InterruptedException {
        queryFromLossCount("2021-02-01", "2021-02-03", "D");
    }
}

//        WebClient client = WebClient.create("http://172.21.90.114:9098");
//
//        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);
//
//        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/dataMarket/onlyTest");
//
//        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue("data");
//
//        WebClient.ResponseSpec responseSpec = headersSpec.header(
//                        HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
//                .acceptCharset(StandardCharsets.UTF_8)
//                .ifNoneMatch("*")
//                .ifModifiedSince(ZonedDateTime.now())
//                .retrieve();
//
//        Mono<String> response = headersSpec.retrieve()
//                .bodyToMono(String.class);

//        WebClient client = WebClient.create("http://172.21.90.114:9098");
//
//        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);
//
//        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/dataMarket/onlyTest");
//
//        Mono<String> response = bodySpec.retrieve()
//                .bodyToMono(String.class);
//        WebClient webClient = WebClient.create();
//        Mono<String> mono = webClient.post().uri("http://rivers-uat.xsyxsc.cn/frmlossapi/noAuth/frmloss/GetFrmLossDetailByTime").retrieve().bodyToMono(String.class);
//        System.out.println(mono.block());
//        WebClient webClient = WebClient.create();



//        JSONPath
//        Object frmLossSkuCount = JsonPath.read(result, "$.data.data[*].frmLossSkuCount");
//        double sumOfFromLoss = JsonPath.read(frmLossSkuCount, "$.sum()");
//
//        Object frmLossTotalNum = JsonPath.read(result, "$.data.data[*].frmLossTotalNum");
//        double sumOfSKUfromLossCount = JsonPath.read(frmLossTotalNum, "$.sum()");
//
//        System.out.println(sumOfFromLoss + ", " + sumOfSKUfromLossCount);
//        System.out.println(frmLossSkuCount + ", " + frmLossTotalNum);
