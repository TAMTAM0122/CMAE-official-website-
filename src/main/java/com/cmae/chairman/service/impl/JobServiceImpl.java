package com.cmae.chairman.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cmae.chairman.entity.Job;
import com.cmae.chairman.mapper.JobMapper;
import com.cmae.chairman.service.IJobService;
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
 * @since 2024-09-14
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements IJobService {
    @Autowired
    private JobMapper jobMapper;

    @Override
    public void addJob(Job job) {
        jobMapper.insert(job);
    }

    @Override
    public void updateJob(Job job) {
        jobMapper.updateById(job);
    }

    @Override
    public List<Job> findJobsByJobName(String jobName) {
        // 使用 MyBatis-Plus 的 LambdaQueryWrapper 进行模糊查询
        LambdaQueryWrapper<Job> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Job::getJob, jobName);  // 模糊匹配
        return jobMapper.selectList(queryWrapper);
    }
}
