package olm.mdm.dataintegration.service.impl;

import com.alibaba.fastjson2.JSON;
import olm.mdm.common.core.domain.entity.DatasourceEntity;
import olm.mdm.common.tool.datax.DataxJsonHelper;
import olm.mdm.dataintegration.domain.JdbcDatasource;
import olm.mdm.common.core.domain.dto.DataXJsonBuildDto;
import olm.mdm.dataintegration.service.IDataxJsonService;
import olm.mdm.dataintegration.service.IJdbcDatasourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * com.wugui.datax json构建实现类
 *
 * @author jingwk
 * @ClassName DataxJsonServiceImpl
 * @Version 2.0
 * @since 2020/01/11 17:15
 */
@Service
public class DataxJsonServiceImpl implements IDataxJsonService {

    @Autowired
    private IJdbcDatasourceService jdbcDatasourceService;

    @Override
    public String buildJobJson(DataXJsonBuildDto dataXJsonBuildDto) {
        DataxJsonHelper dataxJsonHelper = new DataxJsonHelper();
        // reader
        JdbcDatasource readerDatasource = jdbcDatasourceService.selectJdbcDatasourceById(dataXJsonBuildDto.getReaderDatasourceId());
        DatasourceEntity newReaderDatasource = new DatasourceEntity();
        BeanUtils.copyProperties(readerDatasource, newReaderDatasource);
        // reader plugin init
        dataxJsonHelper.initReader(dataXJsonBuildDto, newReaderDatasource);
        JdbcDatasource writerDatasource = jdbcDatasourceService.selectJdbcDatasourceById(dataXJsonBuildDto.getWriterDatasourceId());
        DatasourceEntity newWriterDatasource = new DatasourceEntity();
        BeanUtils.copyProperties(readerDatasource, newWriterDatasource);
        dataxJsonHelper.initWriter(dataXJsonBuildDto, newWriterDatasource);

        return JSON.toJSONString(dataxJsonHelper.buildJob());
    }
}