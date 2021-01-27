package com.zhangxr.springcloud.util.cache;

import com.zhangxr.springcloud.util.cache.cachentites.Cities;
import com.zhangxr.springcloud.util.cache.cachentites.Provinces;
import com.zhangxr.springcloud.util.cache.mapper.CityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className CodeCache
 * @Description 缓存全局变量数据（省、市、区）
 * @Author sdzha
 * @Date 2021/1/26 16:08
 * @Version 1.0
 */
@Component
@Slf4j
public class CodeCache {

    //缓存省份
    public static Map<String, Provinces> provincesMap = new HashMap<String, Provinces>();
    //缓存城市
    public static Map<String, Cities> citiesMap = new HashMap<String, Cities>();
    //缓存城市与省份的关系
    public static Map<String, String> citiesToProvinces = new HashMap<String, String>();

    //注入查询组件
    @Resource
    private CityMapper cityMapper;

//    @PostConstruct
    public void init() {
        log.info("----------加载provincesMap----------");
        List<Provinces> provincesList = cityMapper.getProvincesList();
        for (Provinces provinces : provincesList) {
            log.info("----------加载省份：" + provinces.getProvince() + "----------");
            provincesMap.put(provinces.getProvinceid(), provinces);
        }
        log.info("----------加载citiesMap----------");
        List<Cities> citiesList = cityMapper.getCitiesList();
        for (Cities cities : citiesList) {
            log.info("----------加载城市：" + cities.getCity() + "----------");
            citiesMap.put(cities.getCityid(),cities);
        }
        for (Cities cities : citiesList) {
            log.info("----------加载城市与省份关系：" + cities.getCity() + "----------");
            citiesToProvinces.put(cities.getCityid(),cities.getProvinceid());
        }

    }

//    @PreDestroy
    public void destroy() {
        log.info("---------- 系统运行结束 ----------");
    }

}














