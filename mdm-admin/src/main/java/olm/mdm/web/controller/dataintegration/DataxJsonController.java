package olm.mdm.web.controller.dataintegration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import olm.mdm.common.core.controller.BaseController;
import olm.mdm.common.core.domain.AjaxResult;
import olm.mdm.common.core.domain.dto.DataXJsonBuildDto;
import olm.mdm.dataintegration.service.IDataxJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jingwk on 2020/05/05
 */

@RestController
@RequestMapping("api/dataxJson")
@Api(tags = "组装datax  json的控制器")
public class DataxJsonController extends BaseController {

    @Autowired
    private IDataxJsonService dataxJsonService;


    @PostMapping("/buildJson")
    @ApiOperation("JSON构建")
    public AjaxResult buildJobJson(@RequestBody DataXJsonBuildDto dto) {
        if (dto.getReaderDatasourceId() == null) {
            return error("请选择Reader数据源");
        }
        if (dto.getWriterDatasourceId() == null) {
            return error("请选择Writer数据源");
        }
        if (CollectionUtils.isEmpty(dto.getReaderColumns())) {
            return error("请选择Reader Column");
        }
        if (CollectionUtils.isEmpty(dto.getWriterColumns())) {
            return error("请选择Writer Column");
        }
        return success(dataxJsonService.buildJobJson(dto));
    }

}
