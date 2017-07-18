/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.utils;

import com.audiolib.config.PropertiesConfig;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Util class for MyBatis
 *
 * @author Victor Novikov
 */
public class MyBatisUtils {

    private static SqlSessionFactory sqlMapper;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader(PropertiesConfig.mybatisConfig);
            sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get MyBatis Session object
     *
     * @return
     */
    public static SqlSessionFactory getSession() {
        return sqlMapper;
    }
}
