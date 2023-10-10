package olm.mdm.common.tool.query;


import olm.mdm.common.core.domain.entity.DatasourceEntity;

import java.sql.SQLException;

/**
 * TODO
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName PostgresqlQueryTool
 * @Version 1.0
 * @since 2019/8/2 11:28
 */
public class PostgresqlQueryTool extends BaseQueryTool implements QueryToolInterface {
    public PostgresqlQueryTool(DatasourceEntity jdbcDatasource) throws SQLException {
        super(jdbcDatasource);
    }

}
