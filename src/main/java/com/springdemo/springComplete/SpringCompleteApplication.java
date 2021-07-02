package com.springdemo.springComplete;

import java.util.Arrays;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.springdemo.springComplete.dao.EmployeeDao;
import com.springdemo.springComplete.entity.Employee;


@SpringBootApplication
@EnableEurekaClient
public class SpringCompleteApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(SpringCompleteApplication.class, args);
		EmployeeDao dao=(EmployeeDao) context.getBean("employeeDao");
		Employee e1=new Employee(1001,"ramesh",new Date(), 5464.45f, "sdfds@gmail.com", "developer");
		Employee e2=new Employee(1002,"suresh",new Date(), 5464.45f, "sdfds@gmail.com", "developer");
		Employee e3=new Employee(1003,"dhinsh",new Date(), 5464.45f, "sdfds@gmail.com", "developer");
		dao.save(e1);
		dao.save(e2);
		dao.save(e3);

	}

	//@Bean
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
