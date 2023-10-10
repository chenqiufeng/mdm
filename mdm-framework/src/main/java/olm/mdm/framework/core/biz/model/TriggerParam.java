package olm.mdm.framework.core.biz.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by xuxueli on 16/7/22.
 */
@Data
public class TriggerParam implements Serializable{
    private static final long serialVersionUID = 42L;

    private long jobId;

    private String executorHandler;
    private String executorParams;
    private String executorBlockStrategy;
    private long executorTimeout;

    private long logId;
    private long logDateTime;

    private String glueType;
    private String glueSource;
    private long glueUpdatetime;

    private int broadcastIndex;
    private int broadcastTotal;

    private String jobJson;
    private String processId;

    private String replaceParam;
    private String jvmParam;
    private Date startTime;
    private Date triggerTime;

    private String partitionInfo;

    private long startId;
    private long endId;

    private Integer incrementType;

    private String replaceParamType;

}
