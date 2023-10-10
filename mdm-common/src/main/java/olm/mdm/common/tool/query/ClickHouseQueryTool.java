package olm.mdm.common.tool.query;


import olm.mdm.common.core.domain.entity.DatasourceEntity;

import java.sql.SQLException;

/**
 * ClickHouse
 */

public class ClickHouseQueryTool extends BaseQueryTool implements QueryToolInterface {
    /**
     * 构造方法
     *
     * @param jdbcDatasource
     */
  public ClickHouseQueryTool(DatasourceEntity jdbcDatasource) throws SQLException {
        super(jdbcDatasource);
    }
}
