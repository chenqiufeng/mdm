package olm.mdm.dataintegration.service.impl;

import java.io.IOException;
import java.util.List;

import olm.mdm.common.constant.JdbcConstants;
import olm.mdm.common.core.domain.entity.DatasourceEntity;
import olm.mdm.common.tool.query.BaseQueryTool;
import olm.mdm.common.tool.query.HBaseQueryTool;
import olm.mdm.common.tool.query.MongoDBQueryTool;
import olm.mdm.common.tool.query.QueryToolFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import olm.mdm.dataintegration.mapper.JdbcDatasourceMapper;
import olm.mdm.dataintegration.domain.JdbcDatasource;
import olm.mdm.dataintegration.service.IJdbcDatasourceService;

/**
 * jdbc数据源配置Service业务层处理
 * 
 * @author xugang
 * @date 2023-09-26
 */
@Service
public class JdbcDatasourceServiceImpl implements IJdbcDatasourceService 
{
    @Autowired
    private JdbcDatasourceMapper jdbcDatasourceMapper;

    /**
     * 查询jdbc数据源配置
     * 
     * @param id jdbc数据源配置主键
     * @return jdbc数据源配置
     */
    @Override
    public JdbcDatasource selectJdbcDatasourceById(Long id)
    {
        return jdbcDatasourceMapper.selectJdbcDatasourceById(id);
    }

    /**
     * 查询jdbc数据源配置列表
     * 
     * @param jdbcDatasource jdbc数据源配置
     * @return jdbc数据源配置
     */
    @Override
    public List<JdbcDatasource> selectJdbcDatasourceList(JdbcDatasource jdbcDatasource)
    {
        return jdbcDatasourceMapper.selectJdbcDatasourceList(jdbcDatasource);
    }

    /**
     * 新增jdbc数据源配置
     * 
     * @param jdbcDatasource jdbc数据源配置
     * @return 结果
     */
    @Override
    public int insertJdbcDatasource(JdbcDatasource jdbcDatasource)
    {
        return jdbcDatasourceMapper.insertJdbcDatasource(jdbcDatasource);
    }

    /**
     * 修改jdbc数据源配置
     * 
     * @param jdbcDatasource jdbc数据源配置
     * @return 结果
     */
    @Override
    public int updateJdbcDatasource(JdbcDatasource jdbcDatasource)
    {
        return jdbcDatasourceMapper.updateJdbcDatasource(jdbcDatasource);
    }

    /**
     * 批量删除jdbc数据源配置
     * 
     * @param ids 需要删除的jdbc数据源配置主键
     * @return 结果
     */
    @Override
    public int deleteJdbcDatasourceByIds(Long[] ids)
    {
        return jdbcDatasourceMapper.deleteJdbcDatasourceByIds(ids);
    }

    /**
     * 删除jdbc数据源配置信息
     * 
     * @param id jdbc数据源配置主键
     * @return 结果
     */
    @Override
    public int deleteJdbcDatasourceById(Long id)
    {
        return jdbcDatasourceMapper.deleteJdbcDatasourceById(id);
    }

    @Override
    public Boolean dataSourceTest(JdbcDatasource jdbcDatasource) throws IOException {
        DatasourceEntity newJdbcDatasource = new DatasourceEntity();
        BeanUtils.copyProperties(jdbcDatasource, newJdbcDatasource);
        if (JdbcConstants.HBASE.equals(jdbcDatasource.getDatasource())) {
            return new HBaseQueryTool(newJdbcDatasource).dataSourceTest();
        }
        if (JdbcConstants.MONGODB.equals(jdbcDatasource.getDatasource())) {
            return new MongoDBQueryTool(newJdbcDatasource).dataSourceTest(jdbcDatasource.getDatabaseName());
        }
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(newJdbcDatasource);
        return queryTool.dataSourceTest();
    }
}
