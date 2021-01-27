package com.zhangxr.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhangxr.springcloud.util.cache.CityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className UserController
 * @Description UserController
 * @Author sdzha
 * @Date 2021/1/26 15:39
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/city")
public class CityController {

    @PostMapping(value = "/getInfo")
    public String getInfo(@RequestBody JSONObject jsonObject){
        String id = jsonObject.getString("id");
//        return JSONObject.toJSONString(CityUtils.getProvincesInfoByProvinceId(id));
//        return JSONObject.toJSONString(CityUtils.getProvincesNameByProvinceId(id));
//        return JSONObject.toJSONString(CityUtils.getProvincesList());
//        return JSONObject.toJSONString(CityUtils.getCitiesList());
        return JSONObject.toJSONString(CityUtils.getCitiesList(id));
    }
}
