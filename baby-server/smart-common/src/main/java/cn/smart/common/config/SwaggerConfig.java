//package cn.smart.common.config;
//
//import io.swagger.annotations.ApiOperation;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.Contact;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.VendorExtension;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Ye
// */
//@Configuration
//@EnableSwagger2
//@ComponentScan(basePackages = {"cn.smart.server.module.*.controller"})
//@EnableWebMvc
//public class SwaggerConfig {
//
//
//    @Bean
//    public Docket customDocket() {
//        //
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                //加了ApiOperation注解的方法，生成接口文档
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        Contact contact = new Contact("老王", "https://www.baidu.me", "baidu_666@icloud.com");
//        List<VendorExtension> list = new ArrayList<>();
//        //大标题 title
//        return new ApiInfo("Blog前台API接口",
//                //小标题
//                "Swagger测试demo",
//                //版本
//                "0.0.1",
//                //termsOfServiceUrl
//                "www.baidu.com",
//                //作者
//                contact,
//                //链接显示文字
//                "Blog",
//                //网站链接
//                "https://www.baidu.me",
//                list
//        );
//    }
//}
