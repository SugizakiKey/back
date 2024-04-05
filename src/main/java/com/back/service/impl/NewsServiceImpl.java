package com.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.NewsDao;
import com.back.entity.News;
import com.back.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (News)表服务实现类
 *
 * @author songjie
 * @since 2022-12-03 15:25:15
 */
@Service("newsService")
public class NewsServiceImpl extends ServiceImpl<NewsDao, News> implements NewsService {

    @Resource
    private NewsDao newsDao;


    @Override
    public Map getAllNews() {
        List<News> news = newsDao.selectList(null);
        Map map=new HashMap();
        map.put("news",news);
        return map;
    }

    @Override
    public Map getNewsById(Integer id) {
        News news = newsDao.selectById(id);
        Map map=new HashMap();
        map.put("news",news);
        return map;
    }

    @Override
    public void deleteNewsByIds(Integer ids[]) {
        for (int i=0;i<ids.length;i++){
            newsDao.deleteById(ids[i]);
        }
    }
}

