package com.example.mediamarkbe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
//@EnableSwagger2
public class SpringFoxConfig {




    @Bean
    public Docket api() {
        List<RequestParameter> auth = new ArrayList<>();
        auth.add(authorizationHeader());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.mediamarkbe"))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(auth);
    }

    private RequestParameter authorizationHeader() {

        return new RequestParameterBuilder()
                .name("Authorization")
                .description("Authorization header")
                .in(ParameterType.HEADER)
                .required(false)
                .build();
    }




//    @Bean
//    public Docket api() {
//        List<Parameter> parameters = new ArrayList<Parameter>() {{
//            add(new ParameterBuilder()
//                    .name("Authorization")
//                    .modelRef(new ModelRef("string"))
//                    .parameterType("header")
//                    .defaultValue("token")
//                    .required(true)
//                    .build());
//            add(new ParameterBuilder()
//                    .name("Accept-Language")
//                    .modelRef(new ModelRef("string"))
//                    .parameterType("header")
//                    .defaultValue("vi")
//                    .required(true)
//                    .build());
//        }};
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.cvbackend"))
//                .paths(PathSelectors.any())
//                .build()
//                .pathMapping("")
//                .globalOperationParameters(parameters);
//    }
//            @Bean
//            public Docket api() {
//                return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//            }
}
