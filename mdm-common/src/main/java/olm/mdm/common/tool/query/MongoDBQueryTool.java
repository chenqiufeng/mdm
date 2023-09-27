package olm.mdm.common.tool.query;


import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import olm.mdm.common.core.domain.entity.DatasourceEntity;
import olm.mdm.common.utils.LocalCacheUtil;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MongoDBQueryTool {


    private static MongoClient connection = null;
    private static MongoDatabase collections;


    public MongoDBQueryTool(DatasourceEntity jdbcDatasource) throws IOException {
        if (LocalCacheUtil.get(jdbcDatasource.getDatasourceName()) == null) {
            getDataSource(jdbcDatasource);
        } else {
            connection = (MongoClient) LocalCacheUtil.get(jdbcDatasource.getDatasourceName());
            if (connection == null) {
                LocalCacheUtil.remove(jdbcDatasource.getDatasourceName());
                getDataSource(jdbcDatasource);
            }
        }
        LocalCacheUtil.set(jdbcDatasource.getDatasourceName(), connection, 4 * 60 * 60 * 1000);
    }

    private void getDataSource(DatasourceEntity jdbcDatasource) throws IOException {
        if (StringUtils.isBlank(jdbcDatasource.getJdbcUsername()) && StringUtils.isBlank(jdbcDatasource.getJdbcPassword())) {
            connection = new MongoClient(new MongoClientURI(jdbcDatasource.getJdbcUrl()));
        } else {
            MongoCredential credential = MongoCredential.createCredential(jdbcDatasource.getJdbcUsername(), jdbcDatasource.getDatabaseName(), jdbcDatasource.getJdbcPassword().toCharArray());
            connection = new MongoClient(parseServerAddress(jdbcDatasource.getJdbcUrl()), Arrays.asList(credential));
        }
        collections = connection.getDatabase(jdbcDatasource.getDatabaseName());
    }


    // 关闭连接
    public static void sourceClose() {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * 获取DB名称列表
     *
     * @return
     */
    public List<String> getDBNames() {
        MongoIterable<String> dbs = connection.listDatabaseNames();
        List<String> dbNames = new ArrayList<>();
        dbs.forEach((Block<? super String>) dbNames::add);
        return dbNames;
    }

    /**
     * 测试是否连接成功
     *
     * @return
     */
    public boolean dataSourceTest(String dbName) {
        collections = connection.getDatabase(dbName);
        return collections.listCollectionNames().iterator().hasNext();
    }

    /**
     * 获取Collection名称列表
     *
     * @return
     */
    public List<String> getCollectionNames(String dbName) {
        collections = connection.getDatabase(dbName);
        List<String> collectionNames = new ArrayList<>();
        collections.listCollectionNames().forEach((Block<? super String>) collectionNames::add);
        return collectionNames;
    }

    /**
     * 通过CollectionName查询列
     *
     * @param collectionName
     * @return
     */
    public List<String> getColumns(String collectionName) {
        MongoCollection<Document> collection = collections.getCollection(collectionName);
        Document document = collection.find(new BasicDBObject()).first();
        List<String> list = new ArrayList<>();
        if (null == document || document.size() <= 0) {
            return list;
        }
        document.forEach((k, v) -> {
            if (null != v) {
                String type = v.getClass().getSimpleName();
                list.add(k + ":" + type);
            }
      /*if ("Document".equals(type)) {
        ((Document) v).forEach((k1, v1) -> {
          String simpleName = v1.getClass().getSimpleName();
        });
      } */

        });
        return list;
    }

    /**
     * 判断地址类型是否符合要求
     *
     * @param addressList
     * @return
     */
    private static boolean isHostPortPattern(List<Object> addressList) {
        for (Object address : addressList) {
            String regex = "(\\S+):([0-9]+)";
            if (!((String) address).matches(regex)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 转换为mongo地址协议
     *
     * @param rawAddress
     * @return
     */
    private static List<ServerAddress> parseServerAddress(String rawAddress) throws UnknownHostException {
        List<ServerAddress> addressList = new ArrayList<>();
        for (String address : Arrays.asList(rawAddress.split(","))) {
            String[] tempAddress = address.split(":");
            try {
                ServerAddress sa = new ServerAddress(tempAddress[0], Integer.valueOf(tempAddress[1]));
                addressList.add(sa);
            } catch (Exception e) {
                throw new UnknownHostException();
            }
        }
        return addressList;
    }
}
