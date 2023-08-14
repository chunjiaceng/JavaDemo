package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Data
public class SwaggerConfig{
    @Bean
    public Docket docket(){
        // 选择那些路径和api会生成document
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).pathMapping("/").select()
                // 对所有api进行监控
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).build()
                // 配置token
                .globalOperationParameters(setHeaderToken());

    }
    /**
     * 配置token
     *
     * @return
     */
    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("Authorization").description("token").modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }


    public ApiInfo apiInfo(){
        Contact contact = new Contact("haruka","www.chunjiazz.com","chunjiaceng@gmail.com");

        return  new ApiInfo("Swagger API 文档",                    //文档标题
                "这个是一个 Swagger 接口文档。",              //文档描述
                "v1.0",                                       //文档版本
                "www.chunjiazz.com",                   //队伍的网站地址
                contact,                                              //作者信息
                "Apache 2.0",                                  //许可证
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
