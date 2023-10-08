package olm.mdm.job.service;

import olm.mdm.job.domain.JobLog;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-10-08
 */
public interface IJobLogService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public JobLog selectJobLogById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param jobLog 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<JobLog> selectJobLogList(JobLog jobLog);

    /**
     * 新增【请填写功能名称】
     * 
     * @param jobLog 【请填写功能名称】
     * @return 结果
     */
    public int insertJobLog(JobLog jobLog);

    /**
     * 修改【请填写功能名称】
     * 
     * @param jobLog 【请填写功能名称】
     * @return 结果
     */
    public int updateJobLog(JobLog jobLog);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteJobLogByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJobLogById(Long id);
}
