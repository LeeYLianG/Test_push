package com.xpucsc.servicebase.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;


/**
 * 接口文档配置文件
 * @author Abraham
 */
@Configuration
@EnableOpenApi
@EnableKnife4j
public class SwaggerConfig {
    @Bean
    public Docket getDocument(){
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.regex("/admin/.*").negate())
                .paths(PathSelectors.regex("/error.*").negate())
                .build();
    }

    /**
     * 配置swagger的ApiInfo
     * @return
     */
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfo("计算机科学学院信息管理小程序后台接口文档"
                ,"西安工程大学计算机科学学院学生信息管理小程序后台接口文档"
                ,"1.0"
                ,"www.abrahamqqz.com"
                ,new Contact("Abraham", "www.abrahamqqz.com",
                "abraham2002@163.com")
                ,"Apache 2.0"
                ,"http://www.apache.org/licenses/LICENSE-2.0"
                ,new ArrayList());
    }
}
