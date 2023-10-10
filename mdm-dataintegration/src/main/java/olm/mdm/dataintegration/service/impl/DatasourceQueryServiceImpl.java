package olm.mdm.dataintegration.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import olm.mdm.common.constant.JdbcConstants;
import olm.mdm.common.core.domain.entity.DatasourceEntity;
import olm.mdm.common.tool.query.BaseQueryTool;
import olm.mdm.common.tool.query.HBaseQueryTool;
import olm.mdm.common.tool.query.MongoDBQueryTool;
import olm.mdm.common.tool.query.QueryToolFactory;
import olm.mdm.dataintegration.domain.JdbcDatasource;
import olm.mdm.dataintegration.service.IDatasourceQueryService;
import olm.mdm.dataintegration.service.IJdbcDatasourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * datasource query
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName JdbcDatasourceQueryServiceImpl
 * @Version 1.0
 * @since 2019/7/31 20:51
 */
@Service
public class DatasourceQueryServiceImpl implements IDatasourceQueryService {

    @Autowired
    private IJdbcDatasourceService jdbcDatasourceService;

    @Override
    public List<String> getDBs(Long id) throws IOException {
        //获取数据源对象
        JdbcDatasource datasource = jdbcDatasourceService.selectJdbcDatasourceById(id);
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        DatasourceEntity newJdbcDatasource = new DatasourceEntity();
        BeanUtils.copyProperties(datasource, newJdbcDatasource);
        return new MongoDBQueryTool(newJdbcDatasource).getDBNames();
    }


    @Override
    public List<String> getTables(Long id, String tableSchema) throws IOException {
        //获取数据源对象
        JdbcDatasource datasource = jdbcDatasourceService.selectJdbcDatasourceById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        DatasourceEntity newJdbcDatasource = new DatasourceEntity();
        BeanUtils.copyProperties(datasource, newJdbcDatasource);
        if (JdbcConstants.HBASE.equals(datasource.getDatasource())) {
            return new HBaseQueryTool(newJdbcDatasource).getTableNames();
        } else if (JdbcConstants.MONGODB.equals(datasource.getDatasource())) {
            return new MongoDBQueryTool(newJdbcDatasource).getCollectionNames(datasource.getDatabaseName());
        } else {
            BaseQueryTool qTool = QueryToolFactory.getByDbType(newJdbcDatasource);
            if(StringUtils.isBlank(tableSchema)){
                return qTool.getTableNames();
            }else{
                return qTool.getTableNames(tableSchema);
            }
        }
    }

    @Override
    public List<String> getTableSchema(Long id) {
        //获取数据源对象
        JdbcDatasource datasource = jdbcDatasourceService.selectJdbcDatasourceById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        DatasourceEntity newJdbcDatasource = new DatasourceEntity();
        BeanUtils.copyProperties(datasource, newJdbcDatasource);
        BaseQueryTool qTool = QueryToolFactory.getByDbType(newJdbcDatasource);
        return qTool.getTableSchema();
    }

    @Override
    public List<String> getCollectionNames(long id, String dbName) throws IOException {
        //获取数据源对象
        JdbcDatasource datasource = jdbcDatasourceService.selectJdbcDatasourceById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        DatasourceEntity newJdbcDatasource = new DatasourceEntity();
        BeanUtils.copyProperties(datasource, newJdbcDatasource);
        return new MongoDBQueryTool(newJdbcDatasource).getCollectionNames(dbName);
    }


    @Override
    public List<String> getColumns(Long id, String tableName) throws IOException {
        //获取数据源对象
        JdbcDatasource datasource = jdbcDatasourceService.selectJdbcDatasourceById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        DatasourceEntity newJdbcDatasource = new DatasourceEntity();
        BeanUtils.copyProperties(datasource, newJdbcDatasource);
        if (JdbcConstants.HBASE.equals(datasource.getDatasource())) {
            return new HBaseQueryTool(newJdbcDatasource).getColumns(tableName);
        } else if (JdbcConstants.MONGODB.equals(datasource.getDatasource())) {
            return new MongoDBQueryTool(newJdbcDatasource).getColumns(tableName);
        } else {
            BaseQueryTool queryTool = QueryToolFactory.getByDbType(newJdbcDatasource);
            return queryTool.getColumnNames(tableName, datasource.getDatasource());
        }
    }

    @Override
    public List<String> getColumnsByQuerySql(Long datasourceId, String querySql) throws SQLException {
        //获取数据源对象
        JdbcDatasource datasource = jdbcDatasourceService.selectJdbcDatasourceById(datasourceId);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        DatasourceEntity newJdbcDatasource = new DatasourceEntity();
        BeanUtils.copyProperties(datasource, newJdbcDatasource);
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(newJdbcDatasource);
        return queryTool.getColumnsByQuerySql(querySql);
    }
}
