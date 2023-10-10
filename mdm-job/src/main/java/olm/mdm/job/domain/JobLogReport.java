package olm.mdm.job.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import olm.mdm.common.annotation.Excel;
import olm.mdm.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 job_log_report
 * 
 * @author ruoyi
 * @date 2023-10-08
 */
public class JobLogReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 调度-时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "调度-时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date triggerDay;

    /** 运行中-日志数量 */
    @Excel(name = "运行中-日志数量")
    private Long runningCount;

    /** 执行成功-日志数量 */
    @Excel(name = "执行成功-日志数量")
    private Long sucCount;

    /** 执行失败-日志数量 */
    @Excel(name = "执行失败-日志数量")
    private Long failCount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTriggerDay(Date triggerDay) 
    {
        this.triggerDay = triggerDay;
    }

    public Date getTriggerDay() 
    {
        return triggerDay;
    }
    public void setRunningCount(Long runningCount) 
    {
        this.runningCount = runningCount;
    }

    public Long getRunningCount() 
    {
        return runningCount;
    }
    public void setSucCount(Long sucCount) 
    {
        this.sucCount = sucCount;
    }

    public Long getSucCount() 
    {
        return sucCount;
    }
    public void setFailCount(Long failCount) 
    {
        this.failCount = failCount;
    }

    public Long getFailCount() 
    {
        return failCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("triggerDay", getTriggerDay())
            .append("runningCount", getRunningCount())
            .append("sucCount", getSucCount())
            .append("failCount", getFailCount())
            .toString();
    }
}
