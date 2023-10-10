package olm.mdm.web.controller.datamodel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import olm.mdm.common.annotation.Log;
import olm.mdm.common.core.controller.BaseController;
import olm.mdm.common.core.domain.AjaxResult;
import olm.mdm.common.core.page.TableDataInfo;
import olm.mdm.common.enums.BusinessType;
import olm.mdm.common.utils.poi.ExcelUtil;
import olm.mdm.datamodel.domain.DataModel;
import olm.mdm.datamodel.service.IDataModelService;
import olm.mdm.web.controller.ApiUrlController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 主数据模型
 * 
 * @author ruoyi
 */
@RestController
@Api(tags = {"主数据模型管理"}, description = "主数据模型")
@RequestMapping(ApiUrlController.DATA_MODEL_URL)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DataModelController extends BaseController
{

    private final IDataModelService dataModelService;

    /**
     * 查询主数据模型列表
     */
    @ApiOperation("查询主数据模型列表")
    @PreAuthorize("@ss.hasPermi('datamodel:model:list')")
    @GetMapping("/list")
    public TableDataInfo list(DataModel dataModel) {
        startPage();
        List<DataModel> list = dataModelService.list(new QueryWrapper<DataModel>(dataModel));
        return getDataTable(list);
    }

    /**
     * 导出主数据模型列表
     */
    @ApiOperation("导出主数据模型列表")
    @PreAuthorize("@ss.hasPermi('datamodel:model:export')")
    @Log(title = "主数据模型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DataModel dataModel) {
        List<DataModel> list = dataModelService.list(new QueryWrapper<DataModel>(dataModel));
        ExcelUtil<DataModel> util = new ExcelUtil<DataModel>(DataModel.class);
        return util.exportExcel(list, "主数据模型数据");
    }

    /**
     * 获取主数据模型详细信息
     */
    @ApiOperation("获取主数据模型详细信息")
    @PreAuthorize("@ss.hasPermi('datamodel:model:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(dataModelService.getById(id));
    }

    /**
     * 新增主数据模型
     */
    @ApiOperation("新增主数据模型")
    @PreAuthorize("@ss.hasPermi('datamodel:model:add')")
    @Log(title = "主数据模型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DataModel dataModel) {
        return toAjax(dataModelService.save(dataModel));
    }

    /**
     * 修改主数据模型
     */
    @ApiOperation("修改主数据模型")
    @PreAuthorize("@ss.hasPermi('datamodel:model:edit')")
    @Log(title = "主数据模型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DataModel dataModel) {
        return toAjax(dataModelService.updateById(dataModel));
    }

    /**
     * 删除主数据模型
     */
    @ApiOperation("删除主数据模型")
    @PreAuthorize("@ss.hasPermi('datamodel:model:remove')")
    @Log(title = "主数据模型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(dataModelService.removeByIds(Arrays.asList(ids)));
    }
}
