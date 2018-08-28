package com.framework.loippi.controller.api;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.framework.loippi.api.core.GenericAPIController;
import com.framework.loippi.service.AreaService;
import com.framework.loippi.utils.redis.JedisCache;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller - 通用
 *
 * @author caisz
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/api/common")
public class CommonController extends GenericAPIController {


    @Resource
    private AreaService areaService;
    @Autowired
    private JedisCache jedisCache;


}
