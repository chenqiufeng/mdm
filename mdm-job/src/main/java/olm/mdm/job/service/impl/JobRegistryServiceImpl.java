package olm.mdm.job.service.impl;

import java.util.List;
import olm.mdm.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import olm.mdm.job.mapper.JobRegistryMapper;
import olm.mdm.job.domain.JobRegistry;
import olm.mdm.job.service.IJobRegistryService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-10-08
 */
@Service
public class JobRegistryServiceImpl implements IJobRegistryService 
{
    @Autowired
    private JobRegistryMapper jobRegistryMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public JobRegistry selectJobRegistryById(Long id)
    {
        return jobRegistryMapper.selectJobRegistryById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param jobRegistry 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<JobRegistry> selectJobRegistryList(JobRegistry jobRegistry)
    {
        return jobRegistryMapper.selectJobRegistryList(jobRegistry);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param jobRegistry 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertJobRegistry(JobRegistry jobRegistry)
    {
        return jobRegistryMapper.insertJobRegistry(jobRegistry);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param jobRegistry 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateJobRegistry(JobRegistry jobRegistry)
    {
        jobRegistry.setUpdateTime(DateUtils.getNowDate());
        return jobRegistryMapper.updateJobRegistry(jobRegistry);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJobRegistryByIds(Long[] ids)
    {
        return jobRegistryMapper.deleteJobRegistryByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJobRegistryById(Long id)
    {
        return jobRegistryMapper.deleteJobRegistryById(id);
    }
}
