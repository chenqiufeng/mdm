package olm.mdm.common.core.domain.entity;

import lombok.Data;
import olm.mdm.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * jdbc数据源配置对象 job_jdbc_datasource
 * 
 * @author xugang
 * @date 2023-09-26
 */
@Data
public class DatasourceEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 数据源名称 */
    private String datasourceName;

    /** 数据源 */
    private String datasource;

    /** 数据源分组 */
    private String datasourceGroup;

    /** 数据库名 */
    private String databaseName;

    /** 用户名 */
    private String jdbcUsername;

    /** 密码 */
    private String jdbcPassword;

    /** jdbc url */
    private String jdbcUrl;

    /** jdbc驱动类 */
    private String jdbcDriverClass;

    /** $column.columnComment */
    private String zkAdress;

    /** 状态：0删除 1启用 2禁用 */
    private Integer status;

    /** 创建时间 */
    private Date createDate;

    /** 更新时间 */
    private Date updateDate;

    /** 备注 */
    private String comments;

}
