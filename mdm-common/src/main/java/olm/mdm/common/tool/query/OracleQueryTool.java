package olm.mdm.common.tool.query;


import olm.mdm.common.core.domain.entity.DatasourceEntity;

import java.sql.SQLException;

/**
 * Oracle数据库使用的查询工具
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName MySQLQueryTool
 * @Version 1.0
 * @since 2019/7/18 9:31
 */
public class OracleQueryTool extends BaseQueryTool implements QueryToolInterface {

    public OracleQueryTool(DatasourceEntity jdbcDatasource) throws SQLException {
        super(jdbcDatasource);
    }
}
