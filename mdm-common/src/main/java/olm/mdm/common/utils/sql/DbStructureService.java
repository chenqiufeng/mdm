package olm.mdm.common.utils.sql;

import com.github.pagehelper.util.StringUtil;
import lombok.AllArgsConstructor;
import olm.mdm.common.utils.sql.model.AttributeItemEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;

/**
 * Title：DbStructureService
 * Description：数据库表操作工具类
 *
 * @author Flicker
 * @create 2023/10/7 17:08
 **/
@Component
@AllArgsConstructor
public class DbStructureService {

    private final JdbcTemplate jdbcTemplate;

    /**
     * 创建表
     * @param tableName
     * @param columns
     * @return
     */
    /**
     * CREATE TABLE `xxx` (
     * `id` int NOT NULL AUTO_INCREMENT,
     * `code` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
     * `camera_index` int DEFAULT NULL,
     * `created_by` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
     * `updated_by` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
     * `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
     * `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
     * PRIMARY KEY (`id`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
     */
    public boolean createTable(String tableName, String tableComment, List<AttributeItemEntity> columns) {
        StringBuffer sb = new StringBuffer();
        sb.append("CREATE TABLE IF NOT EXISTS " + tableName + " (\n");
        sb.append("id int8 NOT NULL auto_increment, \n");
        columns.forEach(e -> {
            sb.append(e.getName() + " " + columnType(e.getType(), e.getLength(), e.getPrecision()) + " "
                    + isNullSql(e.getNotNull()) + " " + isDefaultSql(e.getDefaultValue())
                    + " comment '" + e.getComment() + "' ,\n");
        });
        sb.append("create_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间', \n");
        sb.append("primary key (id) \n");
        sb.append(") engine=innodb DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci comment = '" + tableComment + "' ;\n");
        System.out.println("===execute: " + sb);
        return execute(sb);
    }

    /**
     * 给表添加字段
     *
     * @param tableName
     * @param columns
     * @return
     */
    public boolean addColumns(String tableName, List<AttributeItemEntity> columns) {
        StringBuffer sb = new StringBuffer();
        columns.forEach(e -> {
            sb.append("ALTER TABLE " + tableName + " ADD COLUMN " + e.getName() + " "
                    + columnType(e.getType(), e.getLength(), e.getPrecision()) + " "
                    + isNullSql(e.getNotNull()) + " " + isDefaultSql(e.getDefaultValue())
                    + " comment '" + e.getComment() + "' ;\n");
        });
        return execute(sb);
    }

    /**
     * 更新表的字段
     *
     * @param tableName
     * @param columns
     * @param oldColumnMap
     * @return
     */
    public boolean updateColumns(String tableName, List<AttributeItemEntity> columns, Map<Long, String> oldColumnMap) {
        StringBuffer sb = new StringBuffer();
        columns.forEach(e -> {
            String oldName = oldColumnMap.get(e.getId());
            if (oldName != null && !oldName.equals(e.getName())) {
                // ALTER TABLE table_name CHANGE old_column_name new_column_name data_type;
                sb.append("ALTER TABLE " + tableName + " CHANGE " + oldName + " " + e.getName() + " "
                        + columnType(e.getType(), e.getLength(), e.getPrecision()) + ";\n");
            }
            if (e.getComment() != null && !"".equals(e.getComment())) {
                // ALTER TABLE table_name MODIFY column_name data_type COMMENT 'your comment';
                sb.append("ALTER TABLE " + tableName + " MODIFY " + e.getName()
                        + columnType(e.getType(), e.getLength(), e.getPrecision()) + " COMMENT '"
                        + e.getComment() + "';\n");
            }
        });
        return execute(sb);
    }

    /**
     * 删除表的字段
     *
     * @param tableName
     * @param columns
     * @return
     */
    public boolean dropColumns(String tableName, List<AttributeItemEntity> columns) {
        StringBuffer sb = new StringBuffer();
        columns.forEach(e -> {
            // ALTER TABLE table_name DROP column_name;
            sb.append("ALTER TABLE " + tableName + " DROP " + e.getName() + ";\n");
        });
        return execute(sb);
    }

    /**
     * 删除表
     *
     * @param tableNames
     * @return
     */
    public boolean dropTable(List<String> tableNames) {
        StringBuffer sb = new StringBuffer();
        tableNames.forEach(tableName -> {
            sb.append("DROP TABLE IF EXISTS " + tableName + ";");
        });
        return execute(sb);
    }

    /**
     * 获取所有表名称
     */
    public List<String> tableNames() {
        List<String> tableNames = new ArrayList<>();
        try {
            Connection getConnection = jdbcTemplate.getDataSource().getConnection();
            DatabaseMetaData metaData = getConnection.getMetaData();
            ResultSet rs = metaData.getTables(getConnection.getCatalog(), null, null, new String[]{"TABLE"});
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                tableNames.add(tableName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tableNames;
    }

    private String columnType(String type, Integer length, Integer precision) {
        switch (type) {
            case "varchar":
                if (Objects.isNull(length) || length == 0) {
                    length = 200;
                }
                return type + "(" + length + ")";
            case "decimal":
                return type + "(" + length + "," + precision + ")";
            default:
                return type;
        }
    }

    private boolean execute(StringBuffer sb) {
        try {
            jdbcTemplate.execute(sb.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String isNullSql(Boolean boo) {
        if (boo) {
            return "NOT NULL";
        }
        return "";
    }

    private String isDefaultSql(Object obj) {
        if (Objects.nonNull(obj)) {
            if (obj instanceof String) {
                if (StringUtil.isEmpty((String) obj)) {
                    return "";
                }
                return "DEFAULT '" + obj + "'";
            }
            return "DEFAULT " + obj;
        }
        return "";
    }

}