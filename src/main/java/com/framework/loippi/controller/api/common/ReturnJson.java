package com.framework.loippi.controller.api.common;


import java.util.HashMap;
import java.util.Map;

import com.framework.loippi.controller.api.eunm.AppErrors;

import net.sf.json.JSONObject;

/**
 * 报文格式
 */
public class ReturnJson {

    /**
     * 成功
     *
     * @param data 返回数据
     * @return
     */
    public static String jsonStringOk(Object data) {
        Map returnMap = new HashMap();
        returnMap.put("data", data);
        returnMap.put("message", "ok");
        returnMap.put("status", AppConstants.OK);
        JSONObject json = JSONObject.fromObject(returnMap);
        return json.toString();
    }

    /**
     * 成功
     *
     * @return
     */
    public static String jsonStringOk() {
        Map returnMap = new HashMap();
        returnMap.put("message", "ok");
        returnMap.put("status", AppConstants.OK);
        JSONObject json = JSONObject.fromObject(returnMap);
        return json.toString();
    }

    /**
     * 错误
     *
     * @return
     */
    public static String jsonStringError(Object obj) {
        AppErrors appErrors = AppErrors.valueOf(obj.toString());
        Map returnMap = new HashMap();
        returnMap.put("message", appErrors.message);
        returnMap.put("status", appErrors.code);
        JSONObject json = JSONObject.fromObject(returnMap);
        return json.toString();
    }
}
