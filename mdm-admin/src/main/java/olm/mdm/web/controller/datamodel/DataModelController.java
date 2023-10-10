package olm.mdm.web.controller.datamodel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import olm.mdm.common.annotation.Log;
import olm.mdm.common.core.controller.BaseController;
import olm.mdm.common.core.domain.R;
import olm.mdm.common.core.page.TableDataInfo;
import olm.mdm.common.enums.BusinessType;
import olm.mdm.common.utils.poi.ExcelUtil;
import olm.mdm.datamodel.domain.entity.DataModel;
import olm.mdm.datamodel.service.IDataModelService;
import olm.mdm.web.controller.ApiUrlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    @PreAuthorize("@ss.hasPermi('datamodel:model:list')")
    @GetMapping("/list")
    public R<List<DataModel>> list(DataModel dataModel) {
        startPage();
        List<DataModel> list = dataModelService.list(new QueryWrapper<>(dataModel));
        return R.ok(list);
    }

    @ApiOperation("查询主数据模型列表page")
    @PreAuthorize("@ss.hasPermi('datamodel:model:list')")
    @GetMapping("/page")
    public TableDataInfo<DataModel> page(DataModel dataModel) {
        startPage();
        List<DataModel> list = dataModelService.list(new QueryWrapper<>(dataModel));
        return getDataTable(list);
    }

    @ApiOperation("导出主数据模型列表")
    @PreAuthorize("@ss.hasPermi('datamodel:model:export')")
    @Log(title = "主数据模型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R<Boolean> export(DataModel dataModel) {
        List<DataModel> list = dataModelService.list(new QueryWrapper<>(dataModel));
        ExcelUtil<DataModel> util = new ExcelUtil<DataModel>(DataModel.class);
        boolean result = util.exportExcel(list, "主数据模型数据").isSuccess();
        return result ? R.ok(true) : R.fail(false);
    }

    @ApiOperation("获取主数据模型详细信息")
    @PreAuthorize("@ss.hasPermi('datamodel:model:query')")
    @GetMapping(value = "/{id}")
    public R<DataModel> getInfo(@PathVariable("id") Long id) {
        return R.ok(dataModelService.getById(id));
    }

    @ApiOperation("新增主数据模型")
    @PreAuthorize("@ss.hasPermi('datamodel:model:add')")
    @Log(title = "主数据模型", businessType = BusinessType.INSERT)
    @PostMapping
    public R<DataModel> add(@RequestBody DataModel dataModel) {
        boolean result = dataModelService.save(dataModel);
        return result ? R.ok(dataModel) : R.fail();
    }

    @ApiOperation("修改主数据模型")
    @PreAuthorize("@ss.hasPermi('datamodel:model:edit')")
    @Log(title = "主数据模型", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<DataModel> edit(@RequestBody DataModel dataModel) {
        boolean result = dataModelService.updateById(dataModel);
        return result ? R.ok(dataModel) : R.fail();
    }

    @ApiOperation("删除主数据模型")
    @PreAuthorize("@ss.hasPermi('datamodel:model:remove')")
    @Log(title = "主数据模型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Boolean> remove(@PathVariable Long[] ids) {
        boolean result = dataModelService.removeByIds(Arrays.asList(ids));
        return result ? R.ok(true) : R.fail(false);
    }
}
