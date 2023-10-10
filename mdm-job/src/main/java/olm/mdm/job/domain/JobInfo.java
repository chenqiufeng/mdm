package olm.mdm.job.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import olm.mdm.common.annotation.Excel;
import olm.mdm.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 定时任务对象 job_info
 *
 * @author xugang
 * @date 2023-09-28
 */
@Data
public class JobInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * $column.columnComment
     */
    private long id;

    /**
     * 执行器主键ID
     */
    @Excel(name = "执行器主键ID")
    private long jobGroup;

    /**
     * 任务执行CRON
     */
    @Excel(name = "任务执行CRON")
    private String jobCron;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String jobDesc;

    /**
     * 所属项目id
     */
    @Excel(name = "所属项目id")
    private Long projectId;

    /**
     * $column.columnComment
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date addTime;

    /**
     * 修改用户
     */
    @Excel(name = "修改用户")
    private Long userId;

    /**
     * 报警邮件
     */
    @Excel(name = "报警邮件")
    private String alarmEmail;

    /**
     * 执行器路由策略
     */
    @Excel(name = "执行器路由策略")
    private String executorRouteStrategy;

    /**
     * 执行器任务handler
     */
    @Excel(name = "执行器任务handler")
    private String executorHandler;

    /**
     * 执行器任务参数
     */
    @Excel(name = "执行器任务参数")
    private String executorParam;

    /**
     * 阻塞处理策略
     */
    @Excel(name = "阻塞处理策略")
    private String executorBlockStrategy;

    /**
     * 任务执行超时时间，单位分钟
     */
    @Excel(name = "任务执行超时时间，单位分钟")
    private Long executorTimeout;

    /**
     * 失败重试次数
     */
    @Excel(name = "失败重试次数")
    private Long executorFailRetryCount;

    /**
     * GLUE类型
     */
    @Excel(name = "GLUE类型")
    private String glueType;

    /**
     * GLUE源代码
     */
    @Excel(name = "GLUE源代码")
    private String glueSource;

    /**
     * GLUE备注
     */
    @Excel(name = "GLUE备注")
    private String glueRemark;

    /**
     * GLUE更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "GLUE更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date glueUpdatetime;

    /**
     * 子任务ID，多个逗号分隔
     */
    @Excel(name = "子任务ID，多个逗号分隔")
    private String childJobid;

    /**
     * 调度状态：0-停止，1-运行
     */
    @Excel(name = "调度状态：0-停止，1-运行")
    private Integer triggerStatus;

    /**
     * 上次调度时间
     */
    @Excel(name = "上次调度时间")
    private Long triggerLastTime;

    /**
     * 下次调度时间
     */
    @Excel(name = "下次调度时间")
    private Long triggerNextTime;

    /**
     * datax运行脚本
     */
    @Excel(name = "datax运行脚本")
    private String jobJson;

    /**
     * 动态参数
     */
    @Excel(name = "动态参数")
    private String replaceParam;

    /**
     * jvm参数
     */
    @Excel(name = "jvm参数")
    private String jvmParam;

    /**
     * 增量初始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "增量初始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date incStartTime;

    /**
     * 分区信息
     */
    @Excel(name = "分区信息")
    private String partitionInfo;

    /**
     * 最近一次执行状态
     */
    @Excel(name = "最近一次执行状态")
    private Long lastHandleCode;

    /**
     * 增量时间格式
     */
    @Excel(name = "增量时间格式")
    private String replaceParamType;

    /**
     * reader表名称
     */
    @Excel(name = "reader表名称")
    private String readerTable;

    /**
     * 增量表主键
     */
    @Excel(name = "增量表主键")
    private String primaryKey;

    /**
     * 增量初始id
     */
    @Excel(name = "增量初始id")
    private long incStartId;

    /**
     * 增量类型
     */
    @Excel(name = "增量类型")
    private Integer incrementType;

    /**
     * 数据源id
     */
    @Excel(name = "数据源id")
    private Long datasourceId;

}
