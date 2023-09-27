package olm.mdm.common.tool.query;

import olm.mdm.common.constant.JdbcConstants;
import olm.mdm.common.core.domain.entity.DatasourceEntity;
import olm.mdm.common.utils.RdbmsException;

import java.sql.SQLException;

/**
 * 工具类，获取单例实体
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName QueryToolFactory
 * @Version 1.0
 * @since 2019/7/18 9:36
 */
public class QueryToolFactory {

    public static BaseQueryTool getByDbType(DatasourceEntity jdbcDatasource) {
        //获取dbType
        String datasource = jdbcDatasource.getDatasource();
        if (JdbcConstants.MYSQL.equals(datasource)) {
            return getMySQLQueryToolInstance(jdbcDatasource);
        } else if (JdbcConstants.ORACLE.equals(datasource)) {
            return getOracleQueryToolInstance(jdbcDatasource);
        } else if (JdbcConstants.POSTGRESQL.equals(datasource)) {
            return getPostgresqlQueryToolInstance(jdbcDatasource);
        } else if (JdbcConstants.SQL_SERVER.equals(datasource)) {
            return getSqlserverQueryToolInstance(jdbcDatasource);
        }else if (JdbcConstants.HIVE.equals(datasource)) {
            return getHiveQueryToolInstance(jdbcDatasource);
        } else if (JdbcConstants.CLICKHOUSE.equals(datasource)) {
            return getClickHouseQueryToolInstance(jdbcDatasource);
        }else if (JdbcConstants.HBASE20XSQL.equals(datasource)) {
            return getHbase20XsqlQueryToolQueryToolInstance(jdbcDatasource);
        }
        throw new UnsupportedOperationException("找不到该类型: ".concat(datasource));
    }

    private static BaseQueryTool getMySQLQueryToolInstance(DatasourceEntity jdbcDatasource) {
        try {
            return new MySQLQueryTool(jdbcDatasource);
        } catch (Exception e) {
            throw RdbmsException.asConnException(JdbcConstants.MYSQL,
                    e,jdbcDatasource.getJdbcUsername(),jdbcDatasource.getDatasourceName());
        }
    }

    private static BaseQueryTool getOracleQueryToolInstance(DatasourceEntity jdbcDatasource) {
        try {
            return new OracleQueryTool(jdbcDatasource);
        } catch (SQLException e) {
            throw RdbmsException.asConnException(JdbcConstants.ORACLE,
                    e,jdbcDatasource.getJdbcUsername(),jdbcDatasource.getDatasourceName());
        }
    }

    private static BaseQueryTool getPostgresqlQueryToolInstance(DatasourceEntity jdbcDatasource) {
        try {
            return new PostgresqlQueryTool(jdbcDatasource);
        } catch (SQLException e) {
            throw RdbmsException.asConnException(JdbcConstants.POSTGRESQL,
                    e,jdbcDatasource.getJdbcUsername(),jdbcDatasource.getDatasourceName());
        }
    }

    private static BaseQueryTool getSqlserverQueryToolInstance(DatasourceEntity jdbcDatasource) {
        try {
            return new SqlServerQueryTool(jdbcDatasource);
        } catch (SQLException e) {
            throw RdbmsException.asConnException(JdbcConstants.SQL_SERVER,
                    e,jdbcDatasource.getJdbcUsername(),jdbcDatasource.getDatasourceName());
        }
    }

    private static BaseQueryTool getHiveQueryToolInstance(DatasourceEntity jdbcDatasource) {
        try {
            return new HiveQueryTool(jdbcDatasource);
        } catch (SQLException e) {
            throw RdbmsException.asConnException(JdbcConstants.HIVE,
                    e,jdbcDatasource.getJdbcUsername(),jdbcDatasource.getDatasourceName());
        }
    }
    private static BaseQueryTool getClickHouseQueryToolInstance(DatasourceEntity jdbcDatasource) {
        try {
            return new ClickHouseQueryTool(jdbcDatasource);
        } catch (SQLException e) {
            throw RdbmsException.asConnException(JdbcConstants.CLICKHOUSE,
                    e, jdbcDatasource.getJdbcUsername(), jdbcDatasource.getDatasourceName());
        }
    }

    private static Hbase20XsqlQueryTool getHbase20XsqlQueryToolQueryToolInstance(DatasourceEntity jdbcDatasource) {
        try {
            return new Hbase20XsqlQueryTool(jdbcDatasource);
        } catch (SQLException e) {
            throw RdbmsException.asConnException(JdbcConstants.HBASE20XSQL,
                    e, jdbcDatasource.getJdbcUsername(), jdbcDatasource.getDatasourceName());
        }
    }
}
