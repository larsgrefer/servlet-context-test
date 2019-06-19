package de.larsgrefer.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ServletRegistrationBean<ListResourcesServlet> registrationBean(ListResourcesServlet listResourcesServlet) {
        ServletRegistrationBean<ListResourcesServlet> bean = new ServletRegistrationBean<>(listResourcesServlet);

        bean.addUrlMappings("/resources");

        return bean;
    }
}
