package com.zhangxr.springcloud.util;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * @className JasyptUtil
 * @Description SpringBoot 集成 Jasypt 对数据库加密
 * @Author sdzha
 * @Date 2020/12/18 9:59
 * @Version 1.0
 */
public class JasyptUtil {

    /***
     * @Author sdzha
     * @Description Jasypt生成加密结果
     * @Date 2020/12/18 10:00
     * @Param [password, value] [配置文件中设定的加密盐值,加密值]
     * @return java.lang.String
     */
    public static String encyptPwd(String password,String value){
        PooledPBEStringEncryptor encryptor =
                new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));
        String result = encryptor.encrypt(value);
        return result;
    }

    /***
     * @Author sdzha
     * @Description 解密
     * @Date 2020/12/18 10:15
     * @Param [password, value] [配置文件中设定的加密盐值,解密密文]
     * @return java.lang.String
     */
    public static String decyptPwd(String password,String value){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(cryptor(password));
        String result = encryptor.decrypt(value);
        return result;
    }

    public static SimpleStringPBEConfig cryptor(String password){
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        return config;
    }

    public static void main(String[] args) {
        //加密
        String encyPwd = encyptPwd("jasypt", "12345");
        //解密
        String decyPwd = decyptPwd("jasypt", encyPwd);
        System.out.println("密文：" + encyPwd);
        System.out.println("明文：" + decyPwd);

    }
}






















