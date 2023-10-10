package olm.mdm.job.service.impl;

import olm.mdm.common.utils.DateUtils;
import olm.mdm.job.cron.CronExpression;
import olm.mdm.job.domain.JobInfo;
import olm.mdm.job.mapper.JobInfoMapper;
import olm.mdm.job.service.IJobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 定时任务Service业务层处理
 *
 * @author xugang
 * @date 2023-09-28
 */
@Service
public class JobInfoServiceImpl implements IJobInfoService {
    @Autowired
    private JobInfoMapper jobInfoMapper;

    /**
     * 查询定时任务
     *
     * @param id 定时任务主键
     * @return 定时任务
     */
    @Override
    public JobInfo selectJobInfoById(Long id) {
        return jobInfoMapper.selectJobInfoById(id);
    }

    /**
     * 查询定时任务列表
     *
     * @param jobInfo 定时任务
     * @return 定时任务
     */
    @Override
    public List<JobInfo> selectJobInfoList(JobInfo jobInfo) {
        return jobInfoMapper.selectJobInfoList(jobInfo);
    }

    /**
     * 新增定时任务
     *
     * @param jobInfo 定时任务
     * @return 结果
     */
    @Override
    public int insertJobInfo(JobInfo jobInfo) {
        return jobInfoMapper.insertJobInfo(jobInfo);
    }

    /**
     * 修改定时任务
     *
     * @param jobInfo 定时任务
     * @return 结果
     */
    @Override
    public int updateJobInfo(JobInfo jobInfo) {
        jobInfo.setUpdateTime(DateUtils.getNowDate());
        return jobInfoMapper.updateJobInfo(jobInfo);
    }

    /**
     * 批量删除定时任务
     *
     * @param ids 需要删除的定时任务主键
     * @return 结果
     */
    @Override
    public int deleteJobInfoByIds(Long[] ids) {
        return jobInfoMapper.deleteJobInfoByIds(ids);
    }

    /**
     * 删除定时任务信息
     *
     * @param id 定时任务主键
     * @return 结果
     */
    @Override
    public int deleteJobInfoById(Long id) {
        return jobInfoMapper.deleteJobInfoById(id);
    }

    @Override
    public int stop(Long id) {
        JobInfo jobInfo = jobInfoMapper.selectJobInfoById(id);

        jobInfo.setTriggerStatus(0);
        jobInfo.setTriggerLastTime(0L);
        jobInfo.setTriggerNextTime(0L);

        jobInfo.setUpdateTime(new Date());
        return jobInfoMapper.updateJobInfo(jobInfo);
    }

    @Override
    public int start(Long id) {
        JobInfo jobInfo = jobInfoMapper.selectJobInfoById(id);

        // next trigger time (5s后生效，避开预读周期)
        long nextTriggerTime = 0;
        try {
            Date nextValidTime = new CronExpression(jobInfo.getJobCron()).getNextValidTimeAfter(new Date(System.currentTimeMillis() + 5000));
            if (nextValidTime == null) {
                throw new RuntimeException("Cron非法，永远不会触发");
            }
            nextTriggerTime = nextValidTime.getTime();
        } catch (ParseException e) {
            throw new RuntimeException("Cron格式非法" + e.getMessage());
        }

        jobInfo.setTriggerStatus(1);
        jobInfo.setTriggerLastTime(0L);
        jobInfo.setTriggerNextTime(nextTriggerTime);

        jobInfo.setUpdateTime(new Date());
        return jobInfoMapper.updateJobInfo(jobInfo);
    }
}
