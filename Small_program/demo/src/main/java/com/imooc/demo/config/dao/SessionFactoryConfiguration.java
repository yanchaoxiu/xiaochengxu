package com.imooc.demo.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SessionFactoryConfiguration {
    private static String mybatisConfigFile;
    @Value("${mybatis_config_file}")
    public void setMybatisConfigFile(String mybatisConfigFile) {
        SessionFactoryConfiguration.mybatisConfigFile = mybatisConfigFile;
    }


    private static String mapperPath;
    @Value("${mapper_path}")
    public void setMapperPath(String mapperPath) {
        SessionFactoryConfiguration.mapperPath = mapperPath;
    }

    @Value("${entity_package}")
    private String entityPackage;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSearchPath));
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
        return sqlSessionFactoryBean;
    }
}

//package com.imooc.demo.config.dao;
//import java.io.IOException;
//import javax.sql.DataSource;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//
//@Configuration
//public class SessionFactoryConfiguration {
//    // mybatis-config.xml?????????????????????
//    private static String mybatisConfigFile;
//
//    @Value("${mybatis_config_file}")
//    public void setMybatisConfigFile(String mybatisConfigFile) {
//        SessionFactoryConfiguration.mybatisConfigFile = mybatisConfigFile;
//    }
//
//    // mybatis mapper??????????????????
//    private static String mapperPath;
//
//    @Value("${mapper_path}")
//    public void setMapperPath(String mapperPath) {
//        SessionFactoryConfiguration.mapperPath = mapperPath;
//    }
//
//    // ??????????????????package
//    @Value("${entity_package}")
//    private String entityPackage;
//
//    @Autowired
//    private DataSource dataSource;
//
//    /**
//     * ??????sqlSessionFactoryBean ?????? ????????????configtion ??????mapper ???????????? ??????datasource?????????
//     *
//     * @return
//     * @throws IOException
//     */
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        // ??????mybatis configuration ????????????
//        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));
//        // ??????mapper ????????????
//        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
//        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
//        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath));
//        // ??????dataSource
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        // ??????typeAlias ???????????????
//        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);
//        return sqlSessionFactoryBean;
//    }
//
//}
