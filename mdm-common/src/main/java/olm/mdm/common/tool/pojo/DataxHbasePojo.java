package olm.mdm.common.tool.pojo;

import lombok.Data;
import olm.mdm.common.core.domain.dto.Range;
import olm.mdm.common.core.domain.dto.VersionColumn;
import olm.mdm.common.core.domain.entity.DatasourceEntity;

import java.util.List;
import java.util.Map;

@Data
public class DataxHbasePojo {

  private List<Map<String,Object>> columns;

  /**
   * 数据源信息
   */
  private DatasourceEntity jdbcDatasource;


  private String readerHbaseConfig;

  private String readerTable;

  private String readerMode;

  private String readerMaxVersion;

  private Range readerRange;

  private String writerHbaseConfig;

  private String writerTable;

  private String writerMode;

  private VersionColumn writerVersionColumn;

  private String writerRowkeyColumn;
}
