package org.spring_boot_runner;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Hello world!
 *
 */
@SpringBootApplication
//@EnableJpaRepositories("repository")
@EntityScan("javasrc.jgb.model")
@RestController
@ComponentScan(basePackages={"javasrc"})
@EnableTransactionManagement
@EnableSwagger2
public class SpringBootRunner {	
	static ApplicationContext applicationContext;
    public static void main( String[] args ) {
    	applicationContext = SpringApplication.run(SpringBootRunner.class, args);
        System.out.println( "Hello World!" );
    }
    
    private static String CONTACT = "东软社保公共服务团队";
    private static String TERMS_OF_SERVICE_URL = "http://www.neusoft.com";

    /**
     * 可以定义多个组，比如本类中定义把test和demo区分开了
     * （访问页面就可以看到效果了） 
     *
     */
    @Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.groupName("test")
                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")// base，最终调用接口后会和paths拼接在一起
                .select()
                .paths(or(regex("/api/.*")))//过滤的接口
                .build()
                .apiInfo(simisApiInfo());
    }

    /**
     * 业务申报描述
     * 
     * @return
     */
    private ApiInfo simisApiInfo() {
    	 ApiInfo apiInfo = new ApiInfo("SIEA系统 API",//大标题
                 "SIEA系统 REST API, all the applications could access the Object model data via JSON.",//小标题
                 "0.1",//版本
                 CONTACT,
                 "cuixg@neuqsoft.com",//作者
                 "帮助",//链接显示文字
                 TERMS_OF_SERVICE_URL//网站链接
         );

         return apiInfo;
    }
}
