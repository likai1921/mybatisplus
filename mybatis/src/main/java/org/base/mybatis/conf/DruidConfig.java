package org.base.mybatis.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author zhaowenwen
 * @date 2021/1/13
 */
@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置监控服务器
     *
     * @return 返回监控注册的servlet对象
     * @author SimpleWu
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }
}
