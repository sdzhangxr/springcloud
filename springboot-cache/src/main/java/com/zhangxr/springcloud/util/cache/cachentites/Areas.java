package com.zhangxr.springcloud.util.cache.cachentites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className areas
 * @Description 区域实体
 * @Author sdzha
 * @Date 2021/1/26 16:19
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Areas {
    private int id;
    private String areaid;
    private String area;
    private String cityid;
}
