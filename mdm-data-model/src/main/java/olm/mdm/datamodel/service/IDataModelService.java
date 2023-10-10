package olm.mdm.datamodel.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import olm.mdm.datamodel.domain.DataModel;

/**
 * 主数据模型Service接口
 *
 * @author ruoyi
 * @date 2023-10-10
 */
public interface IDataModelService extends IService<DataModel> {
    /**
     * 查询主数据模型
     *
     * @param id 主数据模型主键
     * @return 主数据模型
     */
    public DataModel selectDataModelById(Long id);

    /**
     * 查询主数据模型列表
     *
     * @param dataModel 主数据模型
     * @return 主数据模型集合
     */
    public List<DataModel> selectDataModelList(DataModel dataModel);

    /**
     * 新增主数据模型
     *
     * @param dataModel 主数据模型
     * @return 结果
     */
    public int insertDataModel(DataModel dataModel);

    /**
     * 修改主数据模型
     *
     * @param dataModel 主数据模型
     * @return 结果
     */
    public int updateDataModel(DataModel dataModel);

    /**
     * 批量删除主数据模型
     *
     * @param ids 需要删除的主数据模型主键集合
     * @return 结果
     */
    public int deleteDataModelByIds(Long[] ids);

    /**
     * 删除主数据模型信息
     *
     * @param id 主数据模型主键
     * @return 结果
     */
    public int deleteDataModelById(Long id);
}