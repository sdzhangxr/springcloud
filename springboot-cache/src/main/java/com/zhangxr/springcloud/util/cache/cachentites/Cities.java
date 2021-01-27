package com.zhangxr.springcloud.util.cache.cachentites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className cities
 * @Description 城市实体
 * @Author sdzha
 * @Date 2021/1/26 16:17
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cities {
    private int id;
    private String cityid;
    private String city;
    private String provinceid;
}
