package com.gem.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gem.back.entity.Sucai;
import com.gem.back.entity.SysUser;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Description: 影片接口
 */
@Repository
public interface SucaiMapper extends BaseMapper<Sucai> {

    @Update("update sucai set passtime = null where id = #{value}")
    Integer setpassnull(Integer id);

    @Update("update sucai set inpasstime = null where id = #{value}")
    Integer setinpassnull(Integer id);

}
