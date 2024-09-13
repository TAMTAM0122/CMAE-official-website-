package com.cmae.chairman.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cmae.chairman.entity.News;
import com.cmae.chairman.mapper.NewsMapper;
import com.cmae.chairman.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author TAMTAM
 * @since 2024-09-03
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public void addNew(News news) {
        newsMapper.insert(news);
    }

    @Override
    public void updateNew(News news) {
        newsMapper.updateById(news);
    }

    @Override
    public List<News> getNewsByTypeAndNum(int type, int num) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        // 过滤条件：type
        queryWrapper.eq("Type", type);
        // 根据创建时间降序排序
        queryWrapper.orderByDesc("Createtime");
        // 限制返回的条数
        Page<News> page = new Page<>(1, num);

        // 执行查询
        return newsMapper.selectPage(page, queryWrapper).getRecords();
    }

    @Override
    public News getNew(int id) {
        return newsMapper.selectById(id);
    }
}
