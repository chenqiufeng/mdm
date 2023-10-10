package olm.mdm.job.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import olm.mdm.job.mapper.JobLogReportMapper;
import olm.mdm.job.domain.JobLogReport;
import olm.mdm.job.service.IJobLogReportService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-08
 */
@Service
public class JobLogReportServiceImpl implements IJobLogReportService 
{
    @Autowired
    private JobLogReportMapper jobLogReportMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public JobLogReport selectJobLogReportById(Long id)
    {
        return jobLogReportMapper.selectJobLogReportById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param jobLogReport 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<JobLogReport> selectJobLogReportList(JobLogReport jobLogReport)
    {
        return jobLogReportMapper.selectJobLogReportList(jobLogReport);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param jobLogReport 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertJobLogReport(JobLogReport jobLogReport)
    {
        return jobLogReportMapper.insertJobLogReport(jobLogReport);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param jobLogReport 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateJobLogReport(JobLogReport jobLogReport)
    {
        return jobLogReportMapper.updateJobLogReport(jobLogReport);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJobLogReportByIds(Long[] ids)
    {
        return jobLogReportMapper.deleteJobLogReportByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJobLogReportById(Long id)
    {
        return jobLogReportMapper.deleteJobLogReportById(id);
    }
}
