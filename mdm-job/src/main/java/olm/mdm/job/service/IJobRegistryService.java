package olm.mdm.job.service;

import olm.mdm.job.domain.JobRegistry;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2023-10-08
 */
public interface IJobRegistryService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public JobRegistry selectJobRegistryById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param jobRegistry 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<JobRegistry> selectJobRegistryList(JobRegistry jobRegistry);

    /**
     * 新增【请填写功能名称】
     * 
     * @param jobRegistry 【请填写功能名称】
     * @return 结果
     */
    public int insertJobRegistry(JobRegistry jobRegistry);

    /**
     * 修改【请填写功能名称】
     * 
     * @param jobRegistry 【请填写功能名称】
     * @return 结果
     */
    public int updateJobRegistry(JobRegistry jobRegistry);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteJobRegistryByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJobRegistryById(Long id);
}
