/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.model.mappers;

import com.audiolib.model.entites.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author Victor Novikov
 */
public interface UserMapper {

    final String getUserById = "SELECT * FROM users WHERE id=#{id}";
    final String findUserByName  = "SELECT DISTINCT ON (login) * FROM users WHERE login=#{username}";
    final String insertUsers = "INSERT INTO users(login,pwd,admin) VALUES "
            + "(#{username},#{password},#{admin})";
    final String deleteUser = "DELETE FROM users WHERE id=#{id}";

    final String getAllUsers = "SELECT * FROM users";
    final String isAdmin = "SELECT admin FROM users WHERE id=#{id}";

    @Select(getUserById)
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "admin", column = "admin"),
        @Result(property = "username", column = "login"),
        @Result(property = "password", column = "pwd")})
    User getUserById(int id);

    @Select(findUserByName)
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "admin", column = "admin"),
        @Result(property = "username", column = "login"),
        @Result(property = "password", column = "pwd")})
    User findUserByName(String name);

    @Insert(insertUsers)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Delete(deleteUser)
    void deleteUser(int id);

    @Select(getAllUsers)
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "admin", column = "admin"),
        @Result(property = "username", column = "login"),
        @Result(property = "password", column = "pwd")})
    List<User> getAllUsers();

    @Select(isAdmin)
    boolean isAdmin(int id);
}
