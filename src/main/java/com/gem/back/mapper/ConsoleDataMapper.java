package com.gem.back.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Auther: NoTomato
 * @DATE:2020/3/14 21:32
 * @Description:
 */
@Component
public interface ConsoleDataMapper {

    @Select("select min(regTime) from user")
    Date getUserStartDate();

    @Select("select max(regTime) from user")
    Date getUserEndDate();

    @Select("select count(id) from user where regTime = #{value}")
    String getNum(String date);

    @Select("select min(createtime) from sucai")
    Date getSucaiCreateDate();

    @Select("select max(passtime) from sucai")
    Date getSucaiPasstime();

    @Select("select max(inpasstime) from sucai")
    Date getSucaiInpasstime();

    @Select("select count(id) from sucai where createtime = #{value}")
    String getSucaiNum(String date);

    @Select("select count(passtime) from sucai where passtime = #{value}")
    String getPassNum(String date);

    @Select("select count(inpasstime) from sucai where inpasstime = #{value}")
    String getInPassNum(String date);


}
