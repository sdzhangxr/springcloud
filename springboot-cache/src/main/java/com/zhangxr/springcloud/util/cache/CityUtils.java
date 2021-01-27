package com.zhangxr.springcloud.util.cache;

import com.zhangxr.springcloud.util.cache.cachentites.Cities;
import com.zhangxr.springcloud.util.cache.cachentites.Provinces;

import java.util.ArrayList;
import java.util.List;

/**
 * @className CityUtils
 * @Description 从缓存中获取数据工具类
 * @Author sdzha
 * @Date 2021/1/26 16:33
 * @Version 1.0
 */
public class CityUtils {

    /**
     * @Author sdzha
     * @Description 获取省份集合
     * @Date 2021/1/26 17:19
     * @Param []
     * @return java.util.List<com.zhangxr.springcloud.util.cache.cachentites.Provinces>
     */
    public static List<Provinces> getProvincesList(){
        List<Provinces> list = new ArrayList<Provinces>();
        CodeCache.provincesMap.forEach((key,value) -> {
            list.add(value);
        });
        return list;
    }
    /**
     * @Author sdzha
     * @Description 通过省份Id获取省份完整信息
     * @Date 2021/1/26 16:35
     * @Param [provinceId]
     * @return com.zhangxr.springcloud.util.cache.cachentites.Provinces
     */
    public static Provinces getProvincesInfoByProvinceId(String provinceId){
        return CodeCache.provincesMap.get(provinceId);
    }

    /**
     * @Author sdzha
     * @Description 通过省份Id获取省份名称
     * @Date 2021/1/26 16:37
     * @Param [provinceId]
     * @return java.lang.String
     */
    public static String getProvincesNameByProvinceId(String provinceId){
        return CodeCache.provincesMap.get(provinceId).getProvince();
    }

    /**
     * @Author sdzha
     * @Description 获取城市集合
     * @Date 2021/1/26 17:19
     * @Param []
     * @return java.util.List<com.zhangxr.springcloud.util.cache.cachentites.Cities>
     */
    public static List<Cities> getCitiesList(){
        List<Cities> list = new ArrayList<>();
        CodeCache.citiesMap.forEach((key,value) -> {
            list.add(value);
        });
        return list;
    }

    /**
     * @Author sdzha
     * @Description 获取城市信息通过城市Id
     * @Date 2021/1/26 17:27
     * @Param [citiesId]
     * @return com.zhangxr.springcloud.util.cache.cachentites.Cities
     */
    public static Cities getCitiesInfoById(String citiesId){
        return CodeCache.citiesMap.get(citiesId);
    }

    /**
     * @Author sdzha
     * @Description 获取城市名称通过城市Id
     * @Date 2021/1/26 17:29
     * @Param [citiesId]
     * @return java.lang.String
     */
    public static String getCitiesNameById(String citiesId){
        return CodeCache.citiesMap.get(citiesId).getCity();
    }

    /**
     * @Author sdzha
     * @Description 获取省份下的城市列表
     * @Date 2021/1/26 17:30
     * @Param [provinceId]
     * @return java.util.List<com.zhangxr.springcloud.util.cache.cachentites.Cities>
     */
    public static List<Cities> getCitiesList(String provinceId){
        List<Cities> list = new ArrayList<>();
        CodeCache.citiesToProvinces.forEach((key,values) ->{
            if (provinceId.equals(values)) list.add(CodeCache.citiesMap.get(key));
        });
        return list;
    }

//    public static Provinces getProvincesInfoByCityId(String cityId){
//        Provinces provinces = new Provinces();
//        CodeCache.citiesToProvinces.forEach((key,values) -> {
//            if (cityId.equals(key)) {
//                Provinces p = CodeCache.provincesMap.get(values);
//                provinces = (Provinces)p;
//            }
//        });
//        return provinces;
//    }

}
