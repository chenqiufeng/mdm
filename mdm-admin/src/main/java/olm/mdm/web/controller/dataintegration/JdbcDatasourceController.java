package olm.mdm.web.controller.dataintegration;

import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.ApiOperation;
import olm.mdm.common.utils.poi.ExcelUtil;
import olm.mdm.dataintegration.domain.JdbcDatasource;
import olm.mdm.dataintegration.service.IJdbcDatasourceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import olm.mdm.common.annotation.Log;
import olm.mdm.common.core.controller.BaseController;
import olm.mdm.common.core.domain.AjaxResult;
import olm.mdm.common.enums.BusinessType;
import olm.mdm.common.core.page.TableDataInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import olm.mdm.common.annotation.Log;
import olm.mdm.common.core.controller.BaseController;
import olm.mdm.common.core.domain.AjaxResult;
import olm.mdm.common.core.page.TableDataInfo;
import olm.mdm.common.enums.BusinessType;
import olm.mdm.common.utils.poi.ExcelUtil;
import olm.mdm.dataintegration.domain.JdbcDatasource;
import olm.mdm.dataintegration.service.IJdbcDatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * jdbc数据源配置Controller
 *
 * @author xugang
 * @date 2023-09-26
 */
@RestController
@Api(tags = "数据源配置控制器")
@RequestMapping("/dataintegration/datasource")
public class JdbcDatasourceController extends BaseController
{
    @Autowired
    private IJdbcDatasourceService jdbcDatasourceService;

    /**
     * 查询jdbc数据源配置列表
     */
    @PreAuthorize("@ss.hasPermi('dataintegration:datasource:list')")
    @ApiOperation("查询jdbc数据源配置列表")
    @GetMapping("/list")
    public TableDataInfo list(JdbcDatasource jdbcDatasource)
    {
        startPage();
        List<JdbcDatasource> list = jdbcDatasourceService.selectJdbcDatasourceList(jdbcDatasource);
        return getDataTable(list);
    }

    /**
     * 导出jdbc数据源配置列表
     */
    @PreAuthorize("@ss.hasPermi('dataintegration:datasource:export')")
    @Log(title = "jdbc数据源配置", businessType = BusinessType.EXPORT)
    @ApiOperation("导出jdbc数据源配置列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, JdbcDatasource jdbcDatasource)
    {
        List<JdbcDatasource> list = jdbcDatasourceService.selectJdbcDatasourceList(jdbcDatasource);
        ExcelUtil<JdbcDatasource> util = new ExcelUtil<JdbcDatasource>(JdbcDatasource.class);
        util.exportExcel(response, list, "jdbc数据源配置数据");
    }

    /**
     * 获取jdbc数据源配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('dataintegration:datasource:query')")
    @ApiOperation("获取jdbc数据源配置详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jdbcDatasourceService.selectJdbcDatasourceById(id));
    }

    /**
     * 新增jdbc数据源配置
     */
    @PreAuthorize("@ss.hasPermi('dataintegration:datasource:add')")
    @Log(title = "jdbc数据源配置", businessType = BusinessType.INSERT)
    @ApiOperation("新增jdbc数据源配置")
    @PostMapping
    public AjaxResult add(@RequestBody JdbcDatasource jdbcDatasource)
    {
        return toAjax(jdbcDatasourceService.insertJdbcDatasource(jdbcDatasource));
    }

    /**
     * 修改jdbc数据源配置
     */
    @PreAuthorize("@ss.hasPermi('dataintegration:datasource:edit')")
    @Log(title = "jdbc数据源配置", businessType = BusinessType.UPDATE)
    @ApiOperation("修改jdbc数据源配置")
    @PutMapping
    public AjaxResult edit(@RequestBody JdbcDatasource jdbcDatasource)
    {
        return toAjax(jdbcDatasourceService.updateJdbcDatasource(jdbcDatasource));
    }

    /**
     * 删除jdbc数据源配置
     */
    @PreAuthorize("@ss.hasPermi('dataintegration:datasource:remove')")
    @Log(title = "jdbc数据源配置", businessType = BusinessType.DELETE)
    @ApiOperation("删除jdbc数据源配置")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jdbcDatasourceService.deleteJdbcDatasourceByIds(ids));
    }

    /**
     * 测试数据源
     * @param jdbcDatasource
     * @return
     */
    @PostMapping("/test")
    @Log(title = "jdbc数据源配置", businessType = BusinessType.OTHER)
    @ApiOperation("测试数据源")
    public AjaxResult dataSourceTest (@RequestBody JdbcDatasource jdbcDatasource) throws IOException {
        return toAjax(jdbcDatasourceService.dataSourceTest(jdbcDatasource));
    }
}
