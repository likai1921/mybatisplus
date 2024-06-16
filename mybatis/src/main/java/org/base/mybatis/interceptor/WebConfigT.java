package org.base.mybatis.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhaowenwen
 * @date 2021/1/22
 */
@Configuration
@Slf4j
public class WebConfigT implements WebMvcConfigurer {

    @Autowired
    private WebInterceptorHandel webInterceptorHandel;
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("======支持跨域请求======");
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowCredentials(true).maxAge(3600);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludPath = {"/doc.html"};
        // registry.addInterceptor(webInterceptorHandel).addPathPatterns("/**").excludePathPatterns(excludPath);
        registry.addInterceptor(loginInterceptor).addPathPatterns("/auth/**").excludePathPatterns(excludPath);
    }

}
