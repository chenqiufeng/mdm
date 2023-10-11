package olm.mdm.web.controller.datamodel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import olm.mdm.common.annotation.Log;
import olm.mdm.common.core.controller.BaseController;
import olm.mdm.common.core.domain.R;
import olm.mdm.common.core.page.PageDomain;
import olm.mdm.common.core.page.TableDataInfo;
import olm.mdm.common.core.validation.CreateValidationGroup;
import olm.mdm.common.core.validation.UpdateValidationGroup;
import olm.mdm.common.enums.BusinessType;
import olm.mdm.common.utils.bean.ModelDataMapper;
import olm.mdm.common.utils.poi.ExcelUtil;
import olm.mdm.datamodel.domain.entity.DataModel;
import olm.mdm.datamodel.domain.model.CreatingDataModel;
import olm.mdm.datamodel.domain.model.DataModelData;
import olm.mdm.datamodel.domain.model.FindingDataModel;
import olm.mdm.datamodel.domain.model.UpdatingDataModel;
import olm.mdm.datamodel.service.IDataModelService;
import olm.mdm.web.controller.ApiUrlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 主数据模型
 * 
 * @author ruoyi
 */
@RestController
@Api(tags = {"主数据模型管理"}, value = "主数据模型")
@RequestMapping(ApiUrlController.DATA_MODEL_URL)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DataModelController extends BaseController {

    private final IDataModelService dataModelService;

    @ApiOperation("查询主数据模型列表")
//    @PreAuthorize("@ss.hasPermi('datamodel:model:list')")
    @GetMapping("/list")
    public TableDataInfo<DataModelData> list(PageDomain page, FindingDataModel finding) {
        Page<DataModel> list = dataModelService.selectDataModelList(page, finding);
        return getDataTable(ModelDataMapper.mapAll(list, DataModelData.class), list.getTotal());
    }

    @ApiOperation("导出主数据模型列表")
//    @PreAuthorize("@ss.hasPermi('datamodel:model:export')")
    @Log(title = "主数据模型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R<Boolean> export(DataModel dataModel) {
        List<DataModel> list = dataModelService.list(new QueryWrapper<>(dataModel));
        ExcelUtil<DataModel> util = new ExcelUtil<DataModel>(DataModel.class);
        boolean result = util.exportExcel(list, "主数据模型数据").isSuccess();
        return result ? R.ok(true) : R.fail(false);
    }

    @ApiOperation("获取主数据模型详细信息")
//    @PreAuthorize("@ss.hasPermi('datamodel:model:query')")
    @GetMapping(value = "/{id}")
    public R<DataModelData> getInfo(@PathVariable("id") Long id) {
        DataModel dataModel = dataModelService.selectDataModelById(id);
        return R.ok(ModelDataMapper.map(dataModel, DataModelData.class));
    }

    @ApiOperation("新增主数据模型")
//    @PreAuthorize("@ss.hasPermi('datamodel:model:add')")
    @Log(title = "主数据模型", businessType = BusinessType.INSERT)
    @PostMapping
    @Transactional
    public R<DataModelData> add(@RequestBody @Validated(CreateValidationGroup.class) CreatingDataModel creating) {
        DataModel dataModel = ModelDataMapper.map(creating, DataModel.class);
        DataModel result = dataModelService.insertDataModel(dataModel);
        return R.ok(ModelDataMapper.map(result, DataModelData.class));
    }

    @ApiOperation("修改主数据模型")
//    @PreAuthorize("@ss.hasPermi('datamodel:model:edit')")
    @Log(title = "主数据模型", businessType = BusinessType.UPDATE)
    @PutMapping
    @Transactional
    public R<DataModelData> edit(@RequestBody @Validated(UpdateValidationGroup.class) UpdatingDataModel updating) {
        DataModel dataModel = ModelDataMapper.map(updating, DataModel.class);
        DataModel result = dataModelService.updateDataModel(dataModel);
        return R.ok(ModelDataMapper.map(result, DataModelData.class));
    }

    @ApiOperation("删除主数据模型")
//    @PreAuthorize("@ss.hasPermi('datamodel:model:remove')")
    @Log(title = "主数据模型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @Transactional
    public R<Boolean> remove(@PathVariable Long[] ids) {
        boolean result = dataModelService.deleteDataModelByIds(ids);
        return result ? R.ok(true) : R.fail(false);
    }
}
