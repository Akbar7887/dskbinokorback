package uz.dsk.binokorback.security;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;


@Configuration
public class CorsConfig {


    @Bean
    public FilterRegistrationBean customCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://cab.dsk.uz");
        //config.addAllowedOrigin("https://dsk-service.azurewebsites.net");
        config.addAllowedOrigin("http://localhost:49588");
        config.addAllowedOrigin("https://test.dsk.uz");
        config.addAllowedOrigin("https://dsk.uz");
        config.addAllowedOrigin("https://api.dsk.uz:8089");
//        config.setAllowedOrigins(List.of("https://test.dsk.uz", "http://test.dsk.uz"));

//        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));

        //IMPORTANT #2: I didn't stress enough the importance of this line in my original answer,
        //but it's here where we tell Spring to load this filter at the right point in the chain
        //(with an order of precedence higher than oauth2's filters)
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
