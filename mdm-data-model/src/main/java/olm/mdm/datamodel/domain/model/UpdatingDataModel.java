package olm.mdm.datamodel.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yhmi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdatingDataModel extends BaseDataModel {

    private Long id;
    
}