/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.utils;

import com.audiolib.config.PropertiesConfig;
import com.audiolib.model.mappers.TablesMapper;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
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

    public static final Logger logger = LoggerFactory.getLogger(MyBatisUtils.class);
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

    public static void createDefaultTables() {
        createUserTable();
        createAudioTable();
    }

    public static void createUserTable() {
        boolean isExist = isTableExist("users");
        if (isExist) {
            return;
        }
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            TablesMapper mapper = session.getMapper(TablesMapper.class);
            logger.info("Creating new table with name 'users' ...");
            mapper.createUsersTable();
            logger.info("Inserting into table with name 'users' test values...");
            mapper.insertTestUsers();
            session.commit();
        }
    }

    public static void createAudioTable() {
        boolean isExist = isTableExist("audio");
        if (isExist) {
            return;
        }
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            TablesMapper mapper = session.getMapper(TablesMapper.class);
            logger.info("Creating new table with name 'audio' ...");
            mapper.createAudioTable();
            logger.info("Inserting into table with name 'audio' test values...");
            mapper.insertTestAudio();
            session.commit();
        }
    }

    public static boolean isTableExist(String name) {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            TablesMapper mapper = session.getMapper(TablesMapper.class);
            logger.info("Check for existence table with name '{}' ...", name);
            boolean result = mapper.checkTableExists(name);
            return result;
        }
    }
}
