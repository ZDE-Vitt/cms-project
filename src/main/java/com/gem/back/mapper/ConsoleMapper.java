package com.gem.back.mapper;

import com.gem.back.entity.Sucai;
import com.gem.back.entity.SysUser;
import com.gem.back.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ConsoleMapper{
    @Select("select id,username,telephone,account,regTime,sex from user order by regTime desc")
    List<User> selectAllUser();
    @Select("select id,username,name,email,mobile,status from sys_user")
    List<SysUser> selectAllSysUser();
    @Select("select id,name,money,downnum,favnum,tagid,size,createtime,author,userid,inpasstime,description from sucai where status=1 order by createtime desc")
    List<Sucai> selectNotPreSuCai();
    @Select("select id,name,money,downnum,favnum,tagid,size,createtime,author,userid,inpasstime,description from sucai where status=3 order by createtime desc")
    List<Sucai> selectPreSuCai();
}
