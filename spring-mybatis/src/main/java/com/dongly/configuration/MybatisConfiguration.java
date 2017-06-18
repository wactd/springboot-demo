package com.dongly.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * mybatis和数据源相关配置
 */
@Configuration
@MapperScan(value = "com.dongly.**.mapper", annotationClass = Repository.class)
public class MybatisConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisConfiguration.class);

    private DataSourceModel dataSourceModel;

    // mybatis mapper resource 路径
    private static String MAPPER_PATH = "classpath:com/dongly/**/*Mapper.xml";

    @Autowired
    public MybatisConfiguration(DataSourceModel dataSourceModel) {
        this.dataSourceModel = dataSourceModel;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        /** 设置mybatis configuration 扫描路径 */
        // sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG));

        /** 添加mapper.xml 扫描路径 */
        PathMatchingResourcePatternResolver resolver =
                new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources(MAPPER_PATH));

        /** 设置datasource */
        bean.setDataSource(dataSource());
        try {
            return bean.getObject();
        } catch (Exception e) {
            LOGGER.error("注册SqlSessionFactory失败", e);
            return null;
        }
    }

    /**
     * 配置分页插件
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
        LOGGER.info("====>>>开始加载分页信息====>>>");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.put("reasonable", "true");
        properties.put("helperDialect", "mysql");
        properties.put("supportMethodsArguments", "true");
        properties.put("params", "count=countSql");
        properties.put("autoRuntimeDialect", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }


    /**
     * 注册数据源
     *
     * @return
     */
    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(dataSourceModel.getDriverClassName());
        druidDataSource.setUrl(dataSourceModel.getUrl());
        druidDataSource.setUsername(dataSourceModel.getUsername());
        druidDataSource.setPassword(dataSourceModel.getPassword());
        druidDataSource.setInitialSize(dataSourceModel.getInitialSize());
        druidDataSource.setMinIdle(dataSourceModel.getMinIdle());
        druidDataSource.setMaxActive(dataSourceModel.getMaxActive());
        druidDataSource.setMaxWait(dataSourceModel.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(dataSourceModel.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(dataSourceModel.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(dataSourceModel.getValidationQuery());
        druidDataSource.setTestWhileIdle(dataSourceModel.getTestWhileIdle());
        druidDataSource.setTestOnBorrow(dataSourceModel.getTestOnBorrow());
        druidDataSource.setTestOnReturn(dataSourceModel.getTestOnReturn());
        druidDataSource.setPoolPreparedStatements(dataSourceModel.getPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(dataSourceModel.getMaxPoolPreparedStatementPerConnectionSize());

        try {
            druidDataSource.setFilters(dataSourceModel.getFilters());
        } catch (SQLException e) {
            LOGGER.error("======>>> Druid configuration initialization filter Failure!!!", e);
        }
        return druidDataSource;
    }

    /**
     * 注册一个StatViewServlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServle() {
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 添加初始化参数：initParams
        // 白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow)
        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        // 登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：WebStatFilter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css," +
                "*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
