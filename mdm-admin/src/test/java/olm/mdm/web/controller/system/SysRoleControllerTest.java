package olm.mdm.web.controller.system;

import lombok.val;
import olm.mdm.common.annotation.DataSource;
import olm.mdm.common.core.domain.entity.SysRole;
import olm.mdm.common.enums.DataSourceType;
import olm.mdm.system.service.ISysRoleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * Title：SysRoleControllerTest
 * Description：
 *
 * @author Flicker
 * @create 2023/10/7 14:28
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
class SysRoleControllerTest {

    @Autowired
    private ISysRoleService roleService;

    @Test
    void list() {
        List<SysRole> list =  roleService.selectRoleAll();
        System.out.println("list.size: " + list.size());
    }

    @Test
    @DataSource(value = DataSourceType.BIZ)
    void listBiz() {
        List<SysRole> list =  roleService.selectRoleAll();
        System.out.println("list.size: " + list.size());
    }
}