package olm.mdm.datamodel.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * @author yhmi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataModelData extends BaseDataModel {

    private Long id;

    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}