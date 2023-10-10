package olm.mdm.web.controller.job;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import olm.mdm.common.annotation.Anonymous;
import olm.mdm.common.annotation.Log;
import olm.mdm.common.core.controller.BaseController;
import olm.mdm.common.core.domain.AjaxResult;
import olm.mdm.common.core.domain.dto.TriggerJobDto;
import olm.mdm.common.core.page.TableDataInfo;
import olm.mdm.common.enums.BusinessType;
import olm.mdm.common.utils.poi.ExcelUtil;
import olm.mdm.framework.core.cron.CronExpression;
import olm.mdm.framework.core.thread.JobTriggerPoolHelper;
import olm.mdm.framework.core.trigger.TriggerTypeEnum;
import olm.mdm.framework.util.DateUtil;
import olm.mdm.framework.util.I18nUtil;
import olm.mdm.job.domain.JobInfo;
import olm.mdm.job.service.IJobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务Controller
 *
 * @author xugang
 * @date 2023-09-28
 */
@RestController
@Api(tags = "定时任务控制器")
@RequestMapping("/job/info")
public class JobInfoController extends BaseController {
    @Autowired
    private IJobInfoService jobInfoService;

    /**
     * 查询定时任务列表
     */
    @PreAuthorize("@ss.hasPermi('job:info:list')")
    @ApiOperation("查询定时任务列表")
    @GetMapping("/list")
    public TableDataInfo list(JobInfo jobInfo) {
        startPage();
        List<JobInfo> list = jobInfoService.selectJobInfoList(jobInfo);
        return getDataTable(list);
    }

    /**
     * 导出定时任务列表
     */
    @PreAuthorize("@ss.hasPermi('job:info:export')")
    @Log(title = "定时任务", businessType = BusinessType.EXPORT)
    @ApiOperation("导出定时任务列表")
    @PostMapping("/export")
    public void export(HttpServletResponse response, JobInfo jobInfo) {
        List<JobInfo> list = jobInfoService.selectJobInfoList(jobInfo);
        ExcelUtil<JobInfo> util = new ExcelUtil<JobInfo>(JobInfo.class);
        util.exportExcel(response, list, "定时任务数据");
    }

    /**
     * 获取定时任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('job:info:query')")
    @ApiOperation("获取定时任务详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(jobInfoService.selectJobInfoById(id));
    }

    /**
     * 新增定时任务
     */
    @PreAuthorize("@ss.hasPermi('job:info:add')")
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @ApiOperation("新增定时任务")
    @PostMapping
    public AjaxResult add(@RequestBody JobInfo jobInfo) {
        return toAjax(jobInfoService.insertJobInfo(jobInfo));
    }

    /**
     * 修改定时任务
     */
    @PreAuthorize("@ss.hasPermi('job:info:edit')")
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @ApiOperation("修改定时任务")
    @PutMapping
    public AjaxResult edit(@RequestBody JobInfo jobInfo) {
        return toAjax(jobInfoService.updateJobInfo(jobInfo));
    }

    /**
     * 删除定时任务
     */
    @PreAuthorize("@ss.hasPermi('job:info:remove')")
    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @ApiOperation("删除定时任务")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(jobInfoService.deleteJobInfoByIds(ids));
    }

    @PostMapping("/stop")
    @ApiOperation("停止任务")
    @Anonymous
    public AjaxResult pause(Long id) {
        return toAjax(jobInfoService.stop(id));
    }

    @PostMapping("/start")
    @ApiOperation("开启任务")
    @Anonymous
    public AjaxResult start(Long id) {
        return toAjax(jobInfoService.start(id));
    }

    @PostMapping( "/trigger")
    @ApiOperation("触发任务")
    @Anonymous
    public AjaxResult triggerJob(@RequestBody TriggerJobDto dto) {
        // force cover job param
        String executorParam = dto.getExecutorParam();
        if (executorParam == null) {
            executorParam = "";
        }
        JobTriggerPoolHelper.trigger(dto.getJobId(), TriggerTypeEnum.MANUAL, -1, null, executorParam);
        return AjaxResult.success();
    }

    @GetMapping("/nextTriggerTime")
    @ApiOperation("获取近5次触发时间")
    @Anonymous
    public AjaxResult nextTriggerTime(String cron) {
        List<String> result = new ArrayList<>();
        try {
            CronExpression cronExpression = new CronExpression(cron);
            Date lastTime = new Date();
            for (int i = 0; i < 5; i++) {
                lastTime = cronExpression.getNextValidTimeAfter(lastTime);
                if (lastTime != null) {
                    result.add(DateUtil.formatDateTime(lastTime));
                } else {
                    break;
                }
            }
        } catch (ParseException e) {
            return AjaxResult.error(I18nUtil.getString("jobinfo_field_cron_invalid"));
        }
        return AjaxResult.success(result);
    }
}
