package olm.mdm.datamodel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import olm.mdm.common.core.page.CustomPage;
import olm.mdm.common.core.page.PageDomain;
import olm.mdm.datamodel.domain.entity.DataModel;
import olm.mdm.datamodel.domain.model.FindingDataModel;
import olm.mdm.datamodel.mapper.DataModelMapper;
import olm.mdm.datamodel.service.IDataModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
        return this.getById(id);
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

    @Override
    public Page<DataModel> selectDataModelList(
            PageDomain page,
            FindingDataModel finding
    ) {
        QueryWrapper<DataModel> queryWrapper = new QueryWrapper<>();
        if (finding.getEnabled() != null) {
            queryWrapper.eq("enabled", finding.getEnabled());
        }
        if (finding.getCode() != null) {
            queryWrapper.eq("code", finding.getCode());
        }
        if (finding.getKeyword() != null) {
            queryWrapper.and(it -> {
                it.like("name", finding.getKeyword())
                        .or()
                        .like("code", finding.getKeyword());

            });
        }
        if (finding.getStatus() != null) {
            queryWrapper.eq("status", finding.getStatus());
        }
        return this.page(
                new CustomPage(page.getPageNum(), page.getPageSize(), page.getOrderBy(), null),
                queryWrapper
        );
    }

    /**
     * 新增主数据模型
     *
     * @param dataModel 主数据模型
     * @return 结果
     */
    @Override
    public DataModel insertDataModel(DataModel dataModel) {
        this.save(dataModel);
        return dataModel;
    }

    /**
     * 修改主数据模型
     *
     * @param dataModel 主数据模型
     * @return 结果
     */
    @Override
    public DataModel updateDataModel(DataModel dataModel) {
        this.updateById(dataModel);
        return dataModel;
    }

    /**
     * 批量删除主数据模型
     *
     * @param ids 需要删除的主数据模型主键
     * @return 结果
     */
    @Override
    public boolean deleteDataModelByIds(Long[] ids) {
        return this.removeByIds(Arrays.asList(ids));
    }

    /**
     * 删除主数据模型信息
     *
     * @param id 主数据模型主键
     * @return 结果
     */
    @Override
    public boolean deleteDataModelById(Long id) {
        return this.removeById(id);
    }
}