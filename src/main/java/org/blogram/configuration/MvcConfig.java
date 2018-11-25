package org.blogram.configuration;

import org.blogram.service.member.ValidCustomException;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/test").setViewName("test/memberList");
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {

            @Override
            public Map<String, Object> getErrorAttributes(
                    WebRequest request,
                    boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
                Throwable error = getError(request);
                if (error instanceof ValidCustomException) {
                    errorAttributes.put("errors", ((ValidCustomException)error).getErrors());
                }
                return errorAttributes;
            }
        };
    }
}
