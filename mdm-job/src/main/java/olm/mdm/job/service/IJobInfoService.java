package olm.mdm.job.service;

import java.util.List;
import olm.mdm.job.domain.JobInfo;

/**
 * 定时任务Service接口
 * 
 * @author xugang
 * @date 2023-09-28
 */
public interface IJobInfoService 
{
    /**
     * 查询定时任务
     * 
     * @param id 定时任务主键
     * @return 定时任务
     */
    public JobInfo selectJobInfoById(Long id);

    /**
     * 查询定时任务列表
     * 
     * @param jobInfo 定时任务
     * @return 定时任务集合
     */
    public List<JobInfo> selectJobInfoList(JobInfo jobInfo);

    /**
     * 新增定时任务
     * 
     * @param jobInfo 定时任务
     * @return 结果
     */
    public int insertJobInfo(JobInfo jobInfo);

    /**
     * 修改定时任务
     * 
     * @param jobInfo 定时任务
     * @return 结果
     */
    public int updateJobInfo(JobInfo jobInfo);

    /**
     * 批量删除定时任务
     * 
     * @param ids 需要删除的定时任务主键集合
     * @return 结果
     */
    public int deleteJobInfoByIds(Long[] ids);

    /**
     * 删除定时任务信息
     * 
     * @param id 定时任务主键
     * @return 结果
     */
    public int deleteJobInfoById(Long id);

    /**
     * 停止任务
     * @param id 定时任务主键
     * @return 结果
     */
    public int stop(Long id);

    /**
     * 开启任务
     * @param id 定时任务主键
     * @return 结果
     */
    public int start(Long id);
}
