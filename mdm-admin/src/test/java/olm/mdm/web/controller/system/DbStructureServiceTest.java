package olm.mdm.web.controller.system;

import lombok.val;
import olm.mdm.common.enums.DataSourceType;
import olm.mdm.common.utils.sql.model.AttributeItemEntity;
import olm.mdm.common.utils.sql.DbStructureService;
import olm.mdm.framework.datasource.DynamicDataSourceContextHolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Title：DbStructureServiceTest
 * Description：
 *
 * @author Flicker
 * @create 2023/10/7 17:08
 **/
@SpringBootTest()
@RunWith(SpringRunner.class)
class DbStructureServiceTest {

    @Autowired
    private DbStructureService dbStructureService;

    private final static String testTableName = "test_create";

    @org.junit.jupiter.api.Test
    void tableNames() {
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.BIZ.name());
        List<String> tableList = dbStructureService.tableNames();
        System.out.println("tableList: " + tableList.toString());
    }

    @org.junit.jupiter.api.Test
    void createTable() {
        val entities = new ArrayList<AttributeItemEntity>();
        AttributeItemEntity e1 = new AttributeItemEntity();
        e1.setId(1L);
        e1.setName("name");
        e1.setComment("别名");
        e1.setType("varchar");
        e1.setLength(100);
        e1.setNotNull(true);
        e1.setDefaultValue("111");
        entities.add(e1);

        AttributeItemEntity e2 = new AttributeItemEntity();
        e2.setId(2L);
        e2.setName("amount");
        e2.setComment("金额");
        e2.setType("decimal");
        e2.setLength(10);
        e2.setPrecision(2);
        e2.setNotNull(false);
        e2.setDefaultValue("0");
        entities.add(e2);

        boolean pass = dbStructureService.createTable(testTableName, "测试创建", entities);
        System.out.println("create table pass: " + pass);
    }

    @org.junit.jupiter.api.Test
    void addColumns() {
        val entities = new ArrayList<AttributeItemEntity>();
        AttributeItemEntity e3 = new AttributeItemEntity();
        e3.setId(3L);
        e3.setName("pwd");
        e3.setComment("密码");
        e3.setType("varchar");
        e3.setLength(100);
        e3.setNotNull(false);
        e3.setDefaultValue("s");
        entities.add(e3);
        boolean pass = dbStructureService.addColumns(testTableName, entities);
        System.out.println("addColumns.pass: " + pass);
    }

    @org.junit.jupiter.api.Test
    void updateColumns() {
        Map<Long, String> map = new HashMap<>();
        map.put(0L, "pwd");

        val entitiesNew = new ArrayList<AttributeItemEntity>();
        AttributeItemEntity e3New = new AttributeItemEntity();
        e3New.setId(0L);
        e3New.setName("pwdd");
        e3New.setLength(200);
        e3New.setType("varchar");
        entitiesNew.add(e3New);
        boolean pass = dbStructureService.updateColumns(testTableName, entitiesNew, map);
        System.out.println("updateColumns.pass: " + pass);
    }

    @org.junit.jupiter.api.Test
    void dropColumns() {
        val entitiesNew = new ArrayList<AttributeItemEntity>();
        AttributeItemEntity e3New = new AttributeItemEntity();
        e3New.setName("pwdd");
        entitiesNew.add(e3New);
        boolean pass = dbStructureService.dropColumns(testTableName, entitiesNew);
        System.out.println("dropColumns.pass: " + pass);
    }

    @org.junit.jupiter.api.Test
    void dropTable() {
        List<String> tables = new ArrayList<>();
        tables.add(testTableName);
        boolean pass = dbStructureService.dropTable(tables);
        System.out.println("dropTable.pass: " + pass);
    }
}