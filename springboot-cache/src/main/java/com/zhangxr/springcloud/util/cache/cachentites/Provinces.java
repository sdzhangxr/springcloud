package com.zhangxr.springcloud.util.cache.cachentites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className Provinces
 * @Description 省份实体
 * @Author sdzha
 * @Date 2021/1/26 16:12
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provinces {
    private int id;
    private String provinceid;
    private String province;
}
