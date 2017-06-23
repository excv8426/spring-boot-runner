package org.spring_boot_runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;


/**
 * Hello world!
 *
 */
@SpringBootApplication
//@EnableJpaRepositories("repository")
//@EntityScan("model")
@RestController
public class SpringBootRunner {	
	static ApplicationContext applicationContext;
    public static void main( String[] args ) {
    	applicationContext = SpringApplication.run(SpringBootRunner.class, args);
        System.out.println( "Hello World!" );
    }
}
