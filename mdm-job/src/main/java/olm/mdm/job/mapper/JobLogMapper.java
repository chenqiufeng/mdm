package olm.mdm.job.mapper;

import olm.mdm.job.domain.JobLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * job log
 *
 * @author xuxueli 2016-1-12 18:03:06
 */
@Mapper
public interface JobLogMapper {

    // exist jobId not use jobGroup, not exist use jobGroup
    List<JobLog> pageList(@Param("offset") int offset,
                          @Param("pagesize") int pagesize,
                          @Param("jobGroup") int jobGroup,
                          @Param("jobId") int jobId,
                          @Param("triggerTimeStart") Date triggerTimeStart,
                          @Param("triggerTimeEnd") Date triggerTimeEnd,
                          @Param("logStatus") int logStatus);

    int pageListCount(@Param("offset") int offset,
                      @Param("pagesize") int pagesize,
                      @Param("jobGroup") int jobGroup,
                      @Param("jobId") int jobId,
                      @Param("triggerTimeStart") Date triggerTimeStart,
                      @Param("triggerTimeEnd") Date triggerTimeEnd,
                      @Param("logStatus") int logStatus);

    JobLog load(@Param("id") long id);

    long save(JobLog jobLog);

    int updateTriggerInfo(JobLog jobLog);

    int updateHandleInfo(JobLog jobLog);

    int updateProcessId(@Param("id") long id,
                        @Param("processId") String processId);

    int delete(@Param("jobId") int jobId);

    Map<String, Object> findLogReport(@Param("from") Date from,
                                      @Param("to") Date to);

    List<Long> findClearLogIds(@Param("jobGroup") int jobGroup,
                               @Param("jobId") int jobId,
                               @Param("clearBeforeTime") Date clearBeforeTime,
                               @Param("clearBeforeNum") int clearBeforeNum,
                               @Param("pagesize") int pagesize);

    int clearLog(@Param("logIds") List<Long> logIds);

    List<Long> findFailJobLogIds(@Param("pagesize") int pagesize);

    int updateAlarmStatus(@Param("logId") long logId,
                          @Param("oldAlarmStatus") int oldAlarmStatus,
                          @Param("newAlarmStatus") int newAlarmStatus);

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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJobLogById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJobLogByIds(Long[] ids);

}
