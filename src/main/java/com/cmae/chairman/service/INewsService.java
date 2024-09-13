package com.cmae.chairman.service;

import com.cmae.chairman.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TAMTAM
 * @since 2024-09-03
 */
public interface INewsService extends IService<News> {
    public void addNew(News news);

    public void updateNew(News news);

    public List<News> getNewsByTypeAndNum(int type, int num);

    public News getNew(int id);
}
