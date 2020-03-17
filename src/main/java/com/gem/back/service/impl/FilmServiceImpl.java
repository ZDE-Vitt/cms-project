package com.gem.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gem.back.entity.Film;
import com.gem.back.mapper.FilmMapper;
import com.gem.back.service.IFilmService;
import org.springframework.stereotype.Service;


/**
 * @Auther: jzhang
 * @Date: 2018/10/23 22:33
 * @Description:
 */
@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper,Film> implements IFilmService {
}
