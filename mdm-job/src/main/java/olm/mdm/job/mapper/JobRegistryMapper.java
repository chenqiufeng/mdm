package olm.mdm.job.mapper;

import olm.mdm.job.domain.JobRegistry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by jingwk on 2019/11/17
 */
@Mapper
public interface JobRegistryMapper {

    public List<Integer> findDead(@Param("timeout") int timeout,
                                  @Param("nowTime") Date nowTime);

    public int removeDead(@Param("ids") List<Integer> ids);

    public List<JobRegistry> findAll(@Param("timeout") int timeout,
                                     @Param("nowTime") Date nowTime);

    public int registryUpdate(@Param("registryGroup") String registryGroup,
                              @Param("registryKey") String registryKey,
                              @Param("registryValue") String registryValue,
                              @Param("cpuUsage") double cpuUsage,
                              @Param("memoryUsage") double memoryUsage,
                              @Param("loadAverage") double loadAverage,
                              @Param("updateTime") Date updateTime);

    public int registrySave(@Param("registryGroup") String registryGroup,
                            @Param("registryKey") String registryKey,
                            @Param("registryValue") String registryValue,
                            @Param("cpuUsage") double cpuUsage,
                            @Param("memoryUsage") double memoryUsage,
                            @Param("loadAverage") double loadAverage,
                            @Param("updateTime") Date updateTime);

    public int registryDelete(@Param("registryGroup") String registryGroup,
                              @Param("registryKey") String registryKey,
                              @Param("registryValue") String registryValue);

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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJobRegistryById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJobRegistryByIds(Long[] ids);

}
