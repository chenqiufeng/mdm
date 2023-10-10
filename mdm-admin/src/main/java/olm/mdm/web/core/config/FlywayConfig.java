package olm.mdm.web.core.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Flyway多数据源配置，直接使用若依DynamicDataSource
 * @author yhmi
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class FlywayConfig {

    private final DataSource dataSource;

    @Value("${spring.flyway.locations}")
    private String SQL_LOCATION;

    @Value("${spring.flyway.table}")
    private String VERSION_TABLE;

    @Value("${spring.flyway.baseline-on-migrate}")
    private boolean BASELINE_ON_MIGRATE;

    @Value("${spring.flyway.out-of-order}")
    private boolean OUT_OF_ORDER;

    @Value("${spring.flyway.validate-on-migrate}")
    private boolean VALIDATE_ON_MIGRATE;

    @Bean
    @PostConstruct
    public void migrateOrder() {
        log.info("调用数据库生成工具");
        SQL_LOCATION = SQL_LOCATION.split("/")[0]; // 将路径转换
        olm.mdm.framework.datasource.DynamicDataSource ds = (olm.mdm.framework.datasource.DynamicDataSource) dataSource;
        Map<Object, javax.sql.DataSource> dataSources = ds.getResolvedDataSources();
        dataSources.forEach((k, v) -> {
            log.info("正在执行多数据源生成数据库文件 " + k);
            Flyway flyway = Flyway.configure()
                    .dataSource(v)
                    .locations(SQL_LOCATION + "/" + k)
                    .baselineOnMigrate(BASELINE_ON_MIGRATE)
                    .table(VERSION_TABLE)
                    .outOfOrder(OUT_OF_ORDER)
                    .validateOnMigrate(VALIDATE_ON_MIGRATE)
                    .load();
            flyway.migrate();
        });
    }

}
