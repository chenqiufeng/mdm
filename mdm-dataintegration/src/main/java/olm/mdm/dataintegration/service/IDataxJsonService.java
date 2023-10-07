package olm.mdm.dataintegration.service;

import olm.mdm.common.core.domain.dto.DataXJsonBuildDto;

/**
 * com.wugui.datax json构建服务层接口
 *
 * @author zhouhongfa@gz-yibo.com
 * @version 1.0
 * @since 2019/8/1
 */
public interface IDataxJsonService {

    /**
     * build datax json
     *
     * @param dto
     * @return
     */
    String buildJobJson(DataXJsonBuildDto dto);
}
