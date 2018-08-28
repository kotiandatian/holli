package com.framework.loippi.controller.api.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.loippi.controller.api.eunm.AppErrors;
import com.framework.loippi.utils.redis.JedisCache;

/**
 * controller基类
 */
public class AppBaseController {

    @Resource
    private JedisCache jedisCache;;

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 处理系统异常
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public String Exception(HttpServletRequest request, Exception e) {
        logger.error("****************************" );
        logger.error("系统错误", e);
        String uri = request.getRequestURI();
        String ip = request.getRemoteAddr();
        logger.error("uri=" + uri);
        logger.error("ip=" + ip);
        logger.error("****************************" );
        return ReturnJson.jsonStringError(AppErrors.SYSTEM_ERROR_5000000);
    }
//    public int savePhoneCode(String key, String code) {
//        if (code == null) {
//            return 0;
//        }
//        jedisCache.set(key, code, 60);
//        return 1;
//    }
}
