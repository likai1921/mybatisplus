package org.base.mybatis.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaowenwen
 * @date 2020/12/4
 */
@Configuration
@EnableSwagger2
public class SwaggerCfg {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private boolean isSwaggerEnable = true;

    /*    @Value(value = "${swagger.enabled}")
        Boolean swaggerEnabled;*/
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(isSwaggerEnable)
                .apiInfo(apiInfo()).select()
                // 对所有该包下的Api进行监控，如果想要监控所有的话可以改成any()
                //.apis(RequestHandlerSelectors.basePackage("com.iscas"))
                .apis(RequestHandlerSelectors.any())
                // 对所有路径进行扫描
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(globalOperation());
    }

    private List<Parameter> globalOperation() {
        //添加head参数配置start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        //第一个token为传参的key，第二个token为swagger页面显示的值
        tokenPar.name("Authorization").description("Bearer " + "token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("mybatis-plus").contact("lk")
                //创建人
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
}
