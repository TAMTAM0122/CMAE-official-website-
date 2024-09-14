package com.cmae.chairman.service;

import com.cmae.chairman.entity.Job;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author TAMTAM
 * @since 2024-09-14
 */
public interface IJobService extends IService<Job> {
    public void addJob(Job job);

    public void updateJob(Job job);

    public List<Job> findJobsByJobName(String jobName);
}
