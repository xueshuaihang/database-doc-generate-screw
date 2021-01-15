package org.database.doc;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.ArrayList;

/**
 * 生成Mysql数据库文档
 *
 * @author Shuaihang Xue
 * @date 2021/1/15
 */
public class MysqlDocApplication {

    public static final String driverClassName = "com.mysql.jdbc.Driver";
    public static final String jdbcUrl = "jdbc:mysql://localhost:3306/dbname";
    public static final String userName = "";
    public static final String password = "";
    public static final EngineFileType engineFileType = EngineFileType.HTML;
    public static final String fileOutputDir = "";
    public static final String fileName = "dbname-database-doc";
    public static final String title = "";
    public static final String description = "";
    public static final String version = "1.0";

    public static void main(String[] args) {
        new DocumentationExecute(getConfiguration()).execute();
    }

    /**
     * 获取Screw配置
     *
     * @return {@link Configuration}
     */
    public static Configuration getConfiguration() {
        return Configuration.builder()
                .dataSource(getDataSource())
                .engineConfig(getEngineConfig())
                .produceConfig(getProcessConfig())
                .title(title)
                .version(version)
                .description(description)
                .build();
    }

    /**
     * 获取数据源
     *
     * @return {@link DataSource}
     */
    public static DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(password);
        // 设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", true);
        return new HikariDataSource(hikariConfig);
    }

    /**
     * 获取模板配置
     *
     * @return {@link EngineConfig}
     */
    public static EngineConfig getEngineConfig() {
        return EngineConfig.builder()
                .fileOutputDir(fileOutputDir)
                .fileName(fileName)
                .fileType(engineFileType)
                .openOutputDir(true)
                .produceType(EngineTemplateType.freemarker)
                .build();
    }

    /**
     * 获取数据处理相关配置
     *
     * @return {@link ProcessConfig}
     */
    public static ProcessConfig getProcessConfig() {
        return ProcessConfig.builder()
                // 指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                .designatedTableName(new ArrayList<>())
                .designatedTablePrefix(new ArrayList<>())
                .designatedTablePrefix(new ArrayList<>())
                .ignoreTableName(new ArrayList<>())
                .designatedTablePrefix(new ArrayList<>())
                .ignoreTableSuffix(new ArrayList<>())
                .build();
    }

}
