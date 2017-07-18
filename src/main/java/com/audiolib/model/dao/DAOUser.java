/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.model.dao;

import com.audiolib.model.dao.abstr.DAOUserInterface;
import com.audiolib.model.entites.User;
import com.audiolib.model.mappers.UserMapper;
import com.audiolib.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Victor Novikov
 */
public class DAOUser implements DAOUserInterface {

    @Override
    public User getUserByID(int i) {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.getUserById(i);
        }
    }

    @Override
    public User getUserByName(String name) {
        try (SqlSession session = MyBatisUtils.getSession().openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            return mapper.findUserByName(name);
        }
    }

}
