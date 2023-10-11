package olm.mdm.datamodel.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import olm.mdm.common.core.validation.CreateValidationGroup;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author yhmi
 */
@Data
public class BaseDataModel implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("模型名称")
    @NotBlank(message = "模型名称不能为空", groups = CreateValidationGroup.class)
    private String name;

    @ApiModelProperty("模型编码")
    private String code;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("版本号")
    private Long version;

    @ApiModelProperty("是否启用")
    private Integer enabled;

    @ApiModelProperty("排序值")
    private Long sortIndex;

}