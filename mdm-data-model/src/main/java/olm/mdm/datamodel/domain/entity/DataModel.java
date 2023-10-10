package olm.mdm.datamodel.domain.entity;

import olm.mdm.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import olm.mdm.common.core.domain.BaseEntity;

/**
 * 主数据模型对象 data_model
 *
 * @author ruoyi
 * @date 2023-10-10
 */
@Data
@TableName("data_model")
@ApiModel(value = "data_model对象", description = "主数据模型")
public class DataModel extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @Excel(name = "模型名称")
    @ApiModelProperty("模型名称")
    private String name;

    @Excel(name = "模型编码")
    @ApiModelProperty("模型编码")
    private String code;

    @Excel(name = "描述")
    @ApiModelProperty("描述")
    private String description;

    @Excel(name = "状态")
    @ApiModelProperty("状态")
    private String status;

    @Excel(name = "版本号")
    @ApiModelProperty("版本号")
    private Long version;

    @Excel(name = "是否启用")
    @ApiModelProperty("是否启用")
    private Integer enabled;

    @Excel(name = "排序值")
    @ApiModelProperty("排序值")
    private Long sortIndex;

}