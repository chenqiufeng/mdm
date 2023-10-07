package olm.mdm.common.tool.datax.writer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import olm.mdm.common.constant.Constants;
import olm.mdm.common.core.domain.entity.DatasourceEntity;
import olm.mdm.common.tool.datax.BaseDataxPlugin;
import olm.mdm.common.tool.pojo.DataxHbasePojo;
import olm.mdm.common.tool.pojo.DataxHivePojo;
import olm.mdm.common.tool.pojo.DataxMongoDBPojo;
import olm.mdm.common.tool.pojo.DataxRdbmsPojo;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * datax writer base
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName BaseWriterPlugin
 * @Version 1.0
 * @since 2019/8/2 16:28
 */
public abstract class BaseWriterPlugin extends BaseDataxPlugin {
    @Override
    public Map<String, Object> build(DataxRdbmsPojo plugin) {
        Map<String, Object> writerObj = Maps.newLinkedHashMap();
        writerObj.put("name", getName());

        Map<String, Object> parameterObj = Maps.newLinkedHashMap();
//        parameterObj.put("writeMode", "insert");
        DatasourceEntity jobDatasource = plugin.getJobDatasource();
        parameterObj.put("username", jobDatasource.getJdbcUsername());
        parameterObj.put("password", jobDatasource.getJdbcPassword());
        parameterObj.put("column", plugin.getRdbmsColumns());
        parameterObj.put("preSql", splitSql(plugin.getPreSql()));
        parameterObj.put("postSql", splitSql(plugin.getPostSql()));

        Map<String, Object> connectionObj = Maps.newLinkedHashMap();
        connectionObj.put("table", plugin.getTables());
        connectionObj.put("jdbcUrl", jobDatasource.getJdbcUrl());

        parameterObj.put("connection", ImmutableList.of(connectionObj));
        writerObj.put("parameter", parameterObj);

        return writerObj;
    }

    private String[] splitSql(String sql) {
        String[] sqlArr = null;
        if (StringUtils.isNotBlank(sql)) {
            Pattern p = Pattern.compile("\r\n|\r|\n|\n\r");
            Matcher m = p.matcher(sql);
            String sqlStr = m.replaceAll(Constants.STRING_BLANK);
            sqlArr = sqlStr.split(Constants.SPLIT_COLON);
        }
        return sqlArr;
    }

    @Override
    public Map<String, Object> buildHive(DataxHivePojo dataxHivePojo) {
        return null;
    }


    @Override
    public Map<String, Object> buildHbase(DataxHbasePojo dataxHbasePojo) {
        return null;
    }

    @Override
    public Map<String, Object> buildMongoDB(DataxMongoDBPojo plugin) {
        return null;
    }
}
