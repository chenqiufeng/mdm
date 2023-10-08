package olm.mdm.job.service.impl;

import java.util.List;

import olm.mdm.job.domain.JobGroup;
import olm.mdm.job.mapper.JobGroupMapper;
import olm.mdm.job.service.IJobGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-08
 */
@Service
public class JobGroupServiceImpl implements IJobGroupService
{
    @Autowired
    private JobGroupMapper jobGroupMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public JobGroup selectJobGroupById(Long id)
    {
        return jobGroupMapper.selectJobGroupById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param jobGroup 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<JobGroup> selectJobGroupList(JobGroup jobGroup)
    {
        return jobGroupMapper.selectJobGroupList(jobGroup);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param jobGroup 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertJobGroup(JobGroup jobGroup)
    {
        return jobGroupMapper.insertJobGroup(jobGroup);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param jobGroup 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateJobGroup(JobGroup jobGroup)
    {
        return jobGroupMapper.updateJobGroup(jobGroup);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJobGroupByIds(Long[] ids)
    {
        return jobGroupMapper.deleteJobGroupByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJobGroupById(Long id)
    {
        return jobGroupMapper.deleteJobGroupById(id);
    }
}
