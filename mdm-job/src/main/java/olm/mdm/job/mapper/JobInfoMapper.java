package olm.mdm.job.mapper;

import java.util.Date;
import java.util.List;
import olm.mdm.job.domain.JobInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 定时任务Mapper接口
 * 
 * @author xugang
 * @date 2023-09-28
 */
public interface JobInfoMapper
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
     * 删除定时任务
     * 
     * @param id 定时任务主键
     * @return 结果
     */
    public int deleteJobInfoById(Long id);

    /**
     * 批量删除定时任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJobInfoByIds(Long[] ids);

    int scheduleUpdate(JobInfo xxlJobInfo);

    List<JobInfo> scheduleJobQuery(@Param("maxNextTime") long maxNextTime, @Param("pagesize") int pagesize);

    public int updateLastHandleCode(@Param("id") long id,@Param("lastHandleCode")int lastHandleCode);

    void incrementIdUpdate(@Param("id") long id, @Param("incStartId")Long incStartId);

    int incrementTimeUpdate(@Param("id") long id, @Param("incStartTime") Date incStartTime);
}
