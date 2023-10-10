package olm.mdm.datamodel.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import olm.mdm.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import olm.mdm.datamodel.mapper.DataModelMapper;
import olm.mdm.datamodel.domain.entity.DataModel;
import olm.mdm.datamodel.service.IDataModelService;

/**
 * 主数据模型Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-10
 */
@Service
public class DataModelServiceImpl extends ServiceImpl<DataModelMapper, DataModel> implements IDataModelService {

    @Autowired
    private DataModelMapper dataModelMapper;

    /**
     * 查询主数据模型
     *
     * @param id 主数据模型主键
     * @return 主数据模型
     */
    @Override
    public DataModel selectDataModelById(Long id) {
        return dataModelMapper.selectDataModelById(id);
    }

    /**
     * 查询主数据模型列表
     *
     * @param dataModel 主数据模型
     * @return 主数据模型
     */
    @Override
    public List<DataModel> selectDataModelList(DataModel dataModel) {
        return dataModelMapper.selectDataModelList(dataModel);
    }

    /**
     * 新增主数据模型
     *
     * @param dataModel 主数据模型
     * @return 结果
     */
    @Override
    public int insertDataModel(DataModel dataModel) {
        dataModel.setCreateTime(DateUtils.getNowDate());
        return dataModelMapper.insertDataModel(dataModel);
    }

    /**
     * 修改主数据模型
     *
     * @param dataModel 主数据模型
     * @return 结果
     */
    @Override
    public int updateDataModel(DataModel dataModel) {
        dataModel.setUpdateTime(DateUtils.getNowDate());
        return dataModelMapper.updateDataModel(dataModel);
    }

    /**
     * 批量删除主数据模型
     *
     * @param ids 需要删除的主数据模型主键
     * @return 结果
     */
    @Override
    public int deleteDataModelByIds(Long[] ids) {
        return dataModelMapper.deleteDataModelByIds(ids);
    }

    /**
     * 删除主数据模型信息
     *
     * @param id 主数据模型主键
     * @return 结果
     */
    @Override
    public int deleteDataModelById(Long id) {
        return dataModelMapper.deleteDataModelById(id);
    }
}