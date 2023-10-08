package olm.mdm.common.utils.sql.model;

import lombok.Data;

/**
 * Title：AttributeItemEntity
 * Description：
 *
 * @author Flicker
 * @create 2023/10/7 16:58
 **/
@Data
public class AttributeItemEntity {
    // 编号
    private Long id;
    // 字段
    private String name;
    // 字段类型
    private String type;
    // 字段长度
    private Integer length;
    // 精度
    private Integer precision = 0;
    // 不可空
    private Boolean notNull;
    // 默认值
    private String defaultValue;
    // 注释
    private String comment;
}
