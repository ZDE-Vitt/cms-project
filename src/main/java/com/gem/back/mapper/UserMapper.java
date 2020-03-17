package com.gem.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gem.back.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Auther: NoTomato
 * @DATE:2020/3/14 16:23
 * @Description:
 */
@Component
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where id = #{id}")
    User selectUserById(Integer id);
}
