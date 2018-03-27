package org.spring_boot_runner;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
@EnableJpaRepositories("javasrc.importfiles.repository")
@EntityScan("javasrc.importfiles.model")
@RestController
@ComponentScan(basePackages={"javasrc"})
@EnableTransactionManagement
@EnableSwagger2
@EnableCaching
public class SpringBootRunner {	
	static ApplicationContext applicationContext;
    public static void main( String[] args ) {
    	applicationContext = SpringApplication.run(SpringBootRunner.class, args);
        System.out.println( "Hello World!" );
    }
    
    private static String CONTACT = "sgwewerf";
    private static String TERMS_OF_SERVICE_URL = "http://www.baidu.com";

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
    	 ApiInfo apiInfo = new ApiInfo("大标题",//大标题
                 "小标题.",//小标题
                 "0.1",//版本
                 CONTACT,
                 "作者",//作者
                 "帮助",//链接显示文字
                 TERMS_OF_SERVICE_URL//网站链接
         );

         return apiInfo;
    }
    
    @Bean
	public SimpleCacheManager getSimpleCacheManager(){
		SimpleCacheManager simpleCacheManager=new SimpleCacheManager();
		List<Cache> caches=new ArrayList<>();
		caches.add(new ConcurrentMapCache("aa10"));
		simpleCacheManager.setCaches(caches);
		return simpleCacheManager;
	}
    
    /*@Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }*/
    
    /*@Bean
    public RMQConnectionFactory getRMQConnectionFactory(){
    	RMQConnectionFactory rmqConnectionFactory=new RMQConnectionFactory();
    	rmqConnectionFactory.setHost("172.30.3.20");
    	rmqConnectionFactory.setPort(5672);
    	//rmqConnectionFactory.setVirtualHost("/api/vhosts");
    	rmqConnectionFactory.setPassword("rGvLMszNZbA2");
    	rmqConnectionFactory.setUsername("admin");
    	return rmqConnectionFactory;
    }
    
    @Bean 
	public SingleConnectionFactory getSingleConnectionFactory(){
		System.out.println("初始化singleConnectionFactory。");
		SingleConnectionFactory singleConnectionFactory=new SingleConnectionFactory();
		singleConnectionFactory.setTargetConnectionFactory(getRMQConnectionFactory());
		return singleConnectionFactory;
	}
    
    @Bean(name="myQueue")
    public RMQDestination jmsDestination() {
        RMQDestination jmsDestination = new RMQDestination();
        jmsDestination.setDestinationName("myQueue");
        jmsDestination.setAmqp(true);
        jmsDestination.setAmqpQueueName("rabbitQueueName");
        return jmsDestination;
    }
    
    @Bean
	public DefaultMessageListenerContainer getFirstQueueListenerContainer(){
		System.out.println("初始化队列1ListenerContainer。");
		DefaultMessageListenerContainer defaultMessageListenerContainer=new DefaultMessageListenerContainer();
		defaultMessageListenerContainer.setConnectionFactory(getSingleConnectionFactory());
		defaultMessageListenerContainer.setDestination(jmsDestination());
		return defaultMessageListenerContainer;
	}
    
    @Bean
	public JmsTemplate getJmsTemplate(){
		JmsTemplate jmsTemplate=new JmsTemplate(getSingleConnectionFactory());
		return jmsTemplate;
	}*/
}
