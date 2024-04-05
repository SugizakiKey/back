package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.News;

import java.util.Map;

/**
 * (News)表服务接口
 *
 * @author songjie
 * @since 2022-12-03 15:25:15
 */
public interface NewsService extends IService<News> {
Map getAllNews();
Map getNewsById(Integer id);
void deleteNewsByIds(Integer ids[]);
}

