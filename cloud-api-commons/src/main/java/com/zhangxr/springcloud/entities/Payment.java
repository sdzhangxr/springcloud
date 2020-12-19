package com.zhangxr.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author sdzha
 * @Description 实体类
 * @Date 2020/10/10 9:51
 * @Param
 * @return
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Long id;
    private String serial;
}
