package olm.mdm.job.mapper;

import olm.mdm.job.domain.JobGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xuxueli on 16/9/30.
 */
@Mapper
public interface JobGroupMapper {

    List<JobGroup> findAll();

    List<JobGroup> find(@Param("appName") String appName,
                        @Param("title") String title,
                        @Param("addressList") String addressList);

    int save(JobGroup jobGroup);
    List<JobGroup> findByAddressType(@Param("addressType") Long addressType);

    int update(JobGroup jobGroup);

    int remove(@Param("id") Long id);

    JobGroup load(@Param("id") Long id);

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public JobGroup selectJobGroupById(Long id);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param jobGroup 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<JobGroup> selectJobGroupList(JobGroup jobGroup);

    /**
     * 新增【请填写功能名称】
     *
     * @param jobGroup 【请填写功能名称】
     * @return 结果
     */
    public int insertJobGroup(JobGroup jobGroup);

    /**
     * 修改【请填写功能名称】
     *
     * @param jobGroup 【请填写功能名称】
     * @return 结果
     */
    public int updateJobGroup(JobGroup jobGroup);

    /**
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJobGroupById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJobGroupByIds(Long[] ids);
}
