package olm.mdm.datamodel.domain.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author yhmi
 */
@Data
public class FindingDataModel {

    @ApiModelProperty("关键词")
    private String keyword;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("是否启用")
    private String enabled;

    @ApiModelProperty("状态")
    private String status;

}