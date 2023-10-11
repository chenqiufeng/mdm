package olm.mdm.datamodel.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import olm.mdm.common.annotation.Excel;
import olm.mdm.common.core.domain.BaseEntity;

/**
 * 视图对象 view
 *
 * @author ruoyi
 * @date 2023-10-11
 */
@Data
@TableName("view")
@ApiModel(value = "view对象", description = "视图")
public class View extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @Excel(name = "视图标题")
    @ApiModelProperty("视图标题")
    private String name;

    @Excel(name = "视图代号")
    @ApiModelProperty("视图代号")
    private String code;

    @Excel(name = "描述")
    @ApiModelProperty("描述")
    private String description;

    @Excel(name = "数据库表名")
    @ApiModelProperty("数据库表名")
    private String tableName;

    @Excel(name = "版本号")
    @ApiModelProperty("版本号")
    private Long version;

    @Excel(name = "主数据模型ID")
    @ApiModelProperty("主数据模型ID")
    private Long dataModelId;

    @Excel(name = "是否启用")
    @ApiModelProperty("是否启用")
    private Integer enabled;

    @Excel(name = "排序值")
    @ApiModelProperty("排序值")
    private Long sortIndex;





}