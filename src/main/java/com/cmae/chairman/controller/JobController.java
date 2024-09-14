package com.cmae.chairman.controller;

import com.cmae.chairman.entity.Job;
import com.cmae.chairman.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author TAMTAM
 * @since 2024-09-14
 */
@RestController
@RequestMapping("/Job")
public class JobController {
    @Autowired
    private IJobService jobService;

    @PostMapping("/FindJobsByJob")
    public List<Job> searchJobs(@RequestBody Map<String, String> queryData) {
        String jobName = queryData.get("job");  // 从请求体中获取 job 字段
        System.out.println("接收到的岗位名称：" + jobName);
        return jobService.findJobsByJobName(jobName);
    }

    @GetMapping("/GetJobsAll")
    public List<Job> getAllJobs() {
        return jobService.list();
    }

    @GetMapping("/GetJobsById/{id}")
    public Job getJobById(@PathVariable int id) {
        return jobService.getById(id);
    }

    @PostMapping("/CreateJobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.addJob(job);
        return ResponseEntity.ok("创建成功");
    }

    @PostMapping("/DeleteJobs")
    public ResponseEntity<String> deleteJobById(@RequestParam("id") Integer id) {
        boolean result = jobService.removeById(id);
        if (result) {
            return ResponseEntity.ok("删除成功！");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("岗位未找到！");
        }
    }

    @PostMapping("/ModifiedJobs")
    public ResponseEntity<String> modifiedJob(@RequestBody Job job) {
        jobService.updateJob(job);
        return ResponseEntity.ok("修改成功");
    }

}
