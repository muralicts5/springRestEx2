package com.springdemo.springComplete;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpringCompleteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCompleteApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandRunner(ApplicationContext ctx){
		return (args) -> {
				Arrays.stream(ctx.getBeanDefinitionNames())
					.sorted()
					.forEach(beanName->System.out.println(beanName));
			};

		}
	 
	@Bean
	    public WebMvcConfigurer corsConfigurer()
	    {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**");
	            }
	        };
	    }
}
