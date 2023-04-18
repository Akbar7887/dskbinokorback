package uz.dsk.binokorback.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.dsk.binokorback.filter.CustomAuthenticationFilter;
import uz.dsk.binokorback.filter.CustomAuthorizationFilter;

import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter((authenticationManagerBean()));
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(
                "/api/token/refresh/**",
                "/login", "/login/**",
                "/meneger/get", "/meneger/download/meneger/*",
                "/catalog/get",
                "/imagecatalog/download/catalogs/*",
                "/imagecatalog/get", "/imagecatalog/save",
                "/kompleks/get", "/kompleks/download/**",
                "/make/get", "/make/download/makes/*",
                "/news/get", "/news/download/news/*",
                "/news/download/imagenews/*",
                "/news/**",
                "/job/get",
                "/dom/get",
                "/orderb/**",
                "/ligthuser/get",
                "/imagedata/get", "/imagedata/download/images/*"
        ).permitAll();
        http.authorizeRequests().antMatchers("/api/users/**", "/login/**",
                "/meneger/**",
                "/catalog/**",
                "/imagecatalog/download/catalogs/*",
                "/imagecatalog/**",
                "/imagedata/**",
                "/kompleks/**",
                "/make/*", "/make/delete/*",
                "/news/**",
                "/job/**",
                "/dom/**",
                "/orderb/**",
                "/ligthuser/**",
                "/imagedata/**").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(POST,"/api/users/**", "/login/**",
//                "/meneger/**",
//                "/catalog/**",
//                "/imagecatalog/download/catalogs/*",
//                "/imagecatalog/**",
//                "/imagedata/**",
//                "/kompleks/**",
//                "/make/*","/make/delete/*",
//                "/news/**",
//                "/job/**",
//                "/dom/**",
//                "/orderb/**",
//                "/ligthuser/**",
//                "/imagedata/**").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(PUT,"/api/users/**", "/login/**",
//                "/meneger/**",
//                "/catalog/**",
//                "/imagecatalog/download/catalogs/*",
//                "/imagecatalog/**",
//                "/imagedata/**",
//                "/kompleks/**",
//                "/make/*","/make/delete/*",
//                "/news/**",
//                "/job/**",
//                "/dom/**",
//                "/orderb/**",
//                "/ligthuser/**",
//                "/imagedata/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
