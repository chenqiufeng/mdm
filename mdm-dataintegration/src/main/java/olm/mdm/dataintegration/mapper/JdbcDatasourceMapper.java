package olm.mdm.dataintegration.mapper;

import java.util.List;
import olm.mdm.dataintegration.domain.JdbcDatasource;

/**
 * jdbc数据源配置Mapper接口
 * 
 * @author xugang
 * @date 2023-09-26
 */
public interface JdbcDatasourceMapper 
{
    /**
     * 查询jdbc数据源配置
     * 
     * @param id jdbc数据源配置主键
     * @return jdbc数据源配置
     */
    public JdbcDatasource selectJdbcDatasourceById(Long id);

    /**
     * 查询jdbc数据源配置列表
     * 
     * @param jdbcDatasource jdbc数据源配置
     * @return jdbc数据源配置集合
     */
    public List<JdbcDatasource> selectJdbcDatasourceList(JdbcDatasource jdbcDatasource);

    /**
     * 新增jdbc数据源配置
     * 
     * @param jdbcDatasource jdbc数据源配置
     * @return 结果
     */
    public int insertJdbcDatasource(JdbcDatasource jdbcDatasource);

    /**
     * 修改jdbc数据源配置
     * 
     * @param jdbcDatasource jdbc数据源配置
     * @return 结果
     */
    public int updateJdbcDatasource(JdbcDatasource jdbcDatasource);

    /**
     * 删除jdbc数据源配置
     * 
     * @param id jdbc数据源配置主键
     * @return 结果
     */
    public int deleteJdbcDatasourceById(Long id);

    /**
     * 批量删除jdbc数据源配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJdbcDatasourceByIds(Long[] ids);
}
