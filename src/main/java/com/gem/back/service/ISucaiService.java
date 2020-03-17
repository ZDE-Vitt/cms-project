package com.gem.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gem.back.entity.Sucai;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jzhang
 * @since 2020-02-19
 */
public interface ISucaiService extends IService<Sucai> {
    Integer setpassnull(Integer id);
    Integer setinpassnull(Integer id);

}
