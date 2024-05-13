package com.weblearning.tomato.mapper;

import com.weblearning.tomato.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Insert("insert into user(username, password) values (#{username},#{password})")
    void addUser(User user);

    @Select("select * from user where username = #{username} and password = #{password}")
    User login(User user);


    @Select("select * from user where id = #{id}")
    User getUserById(int id);

}
