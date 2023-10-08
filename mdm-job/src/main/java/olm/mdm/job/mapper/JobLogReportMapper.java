package olm.mdm.job.mapper;

import olm.mdm.job.domain.JobLogReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * job log
 *
 * @author xuxueli 2019-11-22
 */
@Mapper
public interface JobLogReportMapper {

    int save(JobLogReport xxlJobLogReport);

    int update(JobLogReport xxlJobLogReport);

    List<JobLogReport> queryLogReport(@Param("triggerDayFrom") Date triggerDayFrom,
                                      @Param("triggerDayTo") Date triggerDayTo);

    JobLogReport queryLogReportTotal();

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
     * 删除【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJobLogReportById(Long id);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJobLogReportByIds(Long[] ids);

}
