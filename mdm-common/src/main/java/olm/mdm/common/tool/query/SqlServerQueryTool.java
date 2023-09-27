package olm.mdm.common.tool.query;


import olm.mdm.common.core.domain.entity.DatasourceEntity;

import java.sql.SQLException;

/**
 * sql server
 *
 * @author zhouhongfa@gz-yibo.com
 * @version 1.0
 * @since 2019/8/2
 */
public class SqlServerQueryTool extends BaseQueryTool implements QueryToolInterface {
    public SqlServerQueryTool(DatasourceEntity jdbcDatasource) throws SQLException {
        super(jdbcDatasource);
    }
}
