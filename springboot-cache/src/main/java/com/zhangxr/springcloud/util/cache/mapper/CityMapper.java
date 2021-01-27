package com.zhangxr.springcloud.util.cache.mapper;

import com.zhangxr.springcloud.util.cache.cachentites.Cities;
import com.zhangxr.springcloud.util.cache.cachentites.Provinces;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @className UserMapper
 * @Description User查询Mapper
 * @Author sdzha
 * @Date 2021/1/26 15:28
 * @Version 1.0
 */
@Mapper
public interface CityMapper {
    //获取省份列表
    @Select("select id,provinceid,province from provinces")
    List<Provinces> getProvincesList();

    @Select("select id,cityid,city,provinceid from cities")
    List<Cities> getCitiesList();
}
