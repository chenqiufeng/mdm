package olm.mdm.job.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import olm.mdm.job.mapper.JobLogMapper;
import olm.mdm.job.domain.JobLog;
import olm.mdm.job.service.IJobLogService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-08
 */
@Service
public class JobLogServiceImpl implements IJobLogService 
{
    @Autowired
    private JobLogMapper jobLogMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public JobLog selectJobLogById(Long id)
    {
        return jobLogMapper.selectJobLogById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param jobLog 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<JobLog> selectJobLogList(JobLog jobLog)
    {
        return jobLogMapper.selectJobLogList(jobLog);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param jobLog 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertJobLog(JobLog jobLog)
    {
        return jobLogMapper.insertJobLog(jobLog);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param jobLog 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateJobLog(JobLog jobLog)
    {
        return jobLogMapper.updateJobLog(jobLog);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJobLogByIds(Long[] ids)
    {
        return jobLogMapper.deleteJobLogByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJobLogById(Long id)
    {
        return jobLogMapper.deleteJobLogById(id);
    }
}
