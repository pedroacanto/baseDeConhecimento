package org.chamados.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JPAConfiguration.class})
@ComponentScan(basePackages = "org.chamados")
@EnableAutoConfiguration(exclude = {
		SecurityAutoConfiguration.class})
public class ChamadosApplication extends SpringBootServletInitializer{

   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	  return application.sources(JspConfig.class);
   }

	public static void main(String[] args) {
		SpringApplication.run(ChamadosApplication.class, args);
	}
}
