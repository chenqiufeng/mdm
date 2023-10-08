package olm.mdm.web.controller.job;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import olm.mdm.common.utils.poi.ExcelUtil;
import olm.mdm.job.domain.JobInfo;
import olm.mdm.job.service.IJobInfoService;
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

/**
 * 定时任务Controller
 *
 * @author xugang
 * @date 2023-09-28
 */
@RestController
@RequestMapping("/job/info")
public class JobInfoController extends BaseController
{
    @Autowired
    private IJobInfoService jobInfoService;

    /**
     * 查询定时任务列表
     */
    @PreAuthorize("@ss.hasPermi('job:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(JobInfo jobInfo)
    {
        startPage();
        List<JobInfo> list = jobInfoService.selectJobInfoList(jobInfo);
        return getDataTable(list);
    }

    /**
     * 导出定时任务列表
     */
    @PreAuthorize("@ss.hasPermi('job:info:export')")
    @Log(title = "定时任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, JobInfo jobInfo)
    {
        List<JobInfo> list = jobInfoService.selectJobInfoList(jobInfo);
        ExcelUtil<JobInfo> util = new ExcelUtil<JobInfo>(JobInfo.class);
        util.exportExcel(response, list, "定时任务数据");
    }

    /**
     * 获取定时任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('job:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(jobInfoService.selectJobInfoById(id));
    }

    /**
     * 新增定时任务
     */
    @PreAuthorize("@ss.hasPermi('job:info:add')")
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JobInfo jobInfo)
    {
        return toAjax(jobInfoService.insertJobInfo(jobInfo));
    }

    /**
     * 修改定时任务
     */
    @PreAuthorize("@ss.hasPermi('job:info:edit')")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JobInfo jobInfo)
    {
        return toAjax(jobInfoService.updateJobInfo(jobInfo));
    }

    /**
     * 删除定时任务
     */
    @PreAuthorize("@ss.hasPermi('job:info:remove')")
    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(jobInfoService.deleteJobInfoByIds(ids));
    }
}
