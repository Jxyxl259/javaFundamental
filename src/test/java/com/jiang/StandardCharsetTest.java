package com.jiang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import sun.misc.Regexp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author:
 * @create: 2019-02-18 14:52
 */
public class StandardCharsetTest {

    public static void main(String[] args) {
       // System.out.println(StandardCharsets.UTF_8.name());
        String json = "{\"promotionName\":\"非预约抢购中详情页测试\",\"promotionType\":\"0\",\"promotionChannels\":\"\",\"memberId\":\"10111168406\", \"type\": \"1\"}";

        long start = System.currentTimeMillis();

//        String c2cJson = null;
//        try {
//            c2cJson = URLDecoder.decode(json, StandardCharsets.UTF_8.name());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        JSONObject c2cObj = JSON.parseObject(c2cJson);
//        String memberId = (String)c2cObj.get("memberId");
        json = json.substring(json.indexOf("memberId") + 11);
        String memberId = json.substring(0, json.indexOf("\","));



        long end = System.currentTimeMillis();
        System.out.println("耗时" + ( ( end - start ) ) + "毫秒，\nmemberId:" + memberId);
    }

}
