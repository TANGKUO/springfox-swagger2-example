package com.akivamu.example;

import com.akivamu.example.models.Pet;
import com.akivamu.example.repositories.PetRepository;
import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @Resource
    private PetRepository petRepository;

    @PostConstruct
    public void init() {
        petRepository.save(new Pet("Cat 1", Arrays.asList("url1", "url2"), "available"));
        petRepository.save(new Pet("Cat 2", Arrays.asList("url1", "url2"), "pending"));
        petRepository.save(new Pet("Cat 3", Arrays.asList("url1", "url2"), "sold"));
    }

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Pet database API")
                .description("Springfox Swagger2 Example API")
                .contact(new Contact("akivamu", "https://github.com/akivamu", "akivamu@gmail.com"))
                .version("1.0")
                .build();
    }
}