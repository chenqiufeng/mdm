package olm.mdm.job.service;

import olm.mdm.job.domain.JobLogReport;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-10-08
 */
public interface IJobLogReportService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public JobLogReport selectJobLogReportById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param jobLogReport 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<JobLogReport> selectJobLogReportList(JobLogReport jobLogReport);

    /**
     * 新增【请填写功能名称】
     * 
     * @param jobLogReport 【请填写功能名称】
     * @return 结果
     */
    public int insertJobLogReport(JobLogReport jobLogReport);

    /**
     * 修改【请填写功能名称】
     * 
     * @param jobLogReport 【请填写功能名称】
     * @return 结果
     */
    public int updateJobLogReport(JobLogReport jobLogReport);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteJobLogReportByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJobLogReportById(Long id);
}
