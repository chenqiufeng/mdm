package olm.mdm.job.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import olm.mdm.common.annotation.Excel;
import olm.mdm.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 job_registry
 * 
 * @author ruoyi
 * @date 2023-10-08
 */
public class JobRegistry extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String registryGroup;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String registryKey;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String registryValue;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long cpuUsage;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long memoryUsage;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long loadAverage;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRegistryGroup(String registryGroup) 
    {
        this.registryGroup = registryGroup;
    }

    public String getRegistryGroup() 
    {
        return registryGroup;
    }
    public void setRegistryKey(String registryKey) 
    {
        this.registryKey = registryKey;
    }

    public String getRegistryKey() 
    {
        return registryKey;
    }
    public void setRegistryValue(String registryValue) 
    {
        this.registryValue = registryValue;
    }

    public String getRegistryValue() 
    {
        return registryValue;
    }
    public void setCpuUsage(Long cpuUsage) 
    {
        this.cpuUsage = cpuUsage;
    }

    public Long getCpuUsage() 
    {
        return cpuUsage;
    }
    public void setMemoryUsage(Long memoryUsage) 
    {
        this.memoryUsage = memoryUsage;
    }

    public Long getMemoryUsage() 
    {
        return memoryUsage;
    }
    public void setLoadAverage(Long loadAverage) 
    {
        this.loadAverage = loadAverage;
    }

    public Long getLoadAverage() 
    {
        return loadAverage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("registryGroup", getRegistryGroup())
            .append("registryKey", getRegistryKey())
            .append("registryValue", getRegistryValue())
            .append("cpuUsage", getCpuUsage())
            .append("memoryUsage", getMemoryUsage())
            .append("loadAverage", getLoadAverage())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
