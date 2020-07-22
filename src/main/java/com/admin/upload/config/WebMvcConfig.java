package com.admin.upload.config;


import com.admin.upload.common.interceptor.CommonInterceptor;
import com.admin.upload.common.util.FileDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private CommonInterceptor commonInterceptor() {
        return new CommonInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(commonInterceptor())
                .addPathPatterns("/**") // 적용할 url
                .excludePathPatterns("/loginAction") // 제외할 url
                .excludePathPatterns("/css/**", "/images/**", "/js/**", "/error/**"); // 제외할 url

        WebMvcConfigurer.super.addInterceptors(registry);
    }


    @Bean
    MappingJackson2JsonView jsonView(){
        return new MappingJackson2JsonView();
    }


    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return (factory) -> factory.addErrorPages(
                new ErrorPage(HttpStatus.BAD_REQUEST, "/views/errors/errorCode500.jsp"),
                new ErrorPage(HttpStatus.UNAUTHORIZED, "/views/errors/errorCode500.jsp"),
                new ErrorPage(HttpStatus.FORBIDDEN, "/views/errors/errorCode500.jsp"),
                new ErrorPage(HttpStatus.NOT_FOUND, "/views/errors/errorCode404.jsp"),
                new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/views/errors/errorCode500.jsp")

        );

    }





}
