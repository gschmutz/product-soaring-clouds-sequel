package com.soaringclouds.product;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.index.TextIndexDefinition.TextIndexDefinitionBuilder;
import org.springframework.web.WebApplicationInitializer;

import com.soaringclouds.product.model.ProductDO;
import com.soaringclouds.product.service.StorageProperties;

@ComponentScan("com.soaringclouds.product")
//@EnableConfigurationProperties(StorageProperties.class)
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
            
            TextIndexDefinition textIndex = new TextIndexDefinitionBuilder()
            		  .onAllFields()
            		  .named("AllFieldsTextIndex")
            		  .build();

            mongoTemplate.indexOps(ProductDO.class).ensureIndex(textIndex);

            
        };
        
    }
}
