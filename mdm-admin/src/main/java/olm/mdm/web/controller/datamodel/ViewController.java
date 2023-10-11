package olm.mdm.web.controller.datamodel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import olm.mdm.common.annotation.Log;
import olm.mdm.common.core.controller.BaseController;
import olm.mdm.common.core.domain.R;
import olm.mdm.common.core.page.TableDataInfo;
import olm.mdm.common.enums.BusinessType;
import olm.mdm.common.utils.poi.ExcelUtil;
import olm.mdm.datamodel.domain.entity.View;
import olm.mdm.datamodel.service.IViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 视图Controller
 * 
 * @author ruoyi
 * @date 2023-10-11
 */
@RestController
@RequestMapping("/system/view")
@Api(value = "视图控制器", tags = {"视图管理"})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ViewController extends BaseController {

    private final IViewService viewService;

    @ApiOperation("查询视图列表")
    @PreAuthorize("@ss.hasPermi('system:view:list')")
    @GetMapping("/list")
    public TableDataInfo list(View view) {
        startPage();
        List<View> list = viewService.selectViewList(view);
        return getDataTable(list);
    }

    @ApiOperation("导出视图列表")
    @PreAuthorize("@ss.hasPermi('system:view:export')")
    @Log(title = "视图", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public R<Boolean> export(View view) {
        List<View> list = viewService.selectViewList(view);
        ExcelUtil<View> util = new ExcelUtil<View>(View.class);
        boolean result = util.exportExcel(list, "视图数据").isSuccess();
        return result ? R.ok(true) : R.fail(false);
    }

    @ApiOperation("获取视图详细信息")
    @PreAuthorize("@ss.hasPermi('system:view:query')")
    @GetMapping(value = "/{id}")
    public R<View> getInfo(@PathVariable("id") Long id) {
        return R.ok(viewService.selectViewById(id));
    }

    @ApiOperation("新增视图")
    @PreAuthorize("@ss.hasPermi('system:view:add')")
    @Log(title = "视图", businessType = BusinessType.INSERT)
    @PostMapping
    public R<View> add(@RequestBody View view) {
        viewService.insertView(view);
        return R.ok(view);
    }

    @ApiOperation("修改视图")
    @PreAuthorize("@ss.hasPermi('system:view:edit')")
    @Log(title = "视图", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<View> edit(@RequestBody View view) {
        viewService.updateView(view);
        return R.ok(view);
    }

    @ApiOperation("删除视图")
    @PreAuthorize("@ss.hasPermi('system:view:remove')")
    @Log(title = "视图", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public R<Boolean> remove(@PathVariable Long[] ids) {
        viewService.deleteViewByIds(ids);
        return R.ok(true);
    }
}
