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
                "/meneger/v1/**",
                "/catalog/v1/**",
                "/imagecatalog/v1/**",
                "/imagecatalog/v1/**",
                "/kompleks/v1/**",
                "/make/v1/**",
                "/news/v1/**",
                "/news/v1/**",
                "/news/v1/**",
                "/job/v1/**",
                "/dom/v1/**",
                "/orderb/v1/*8",
                "/ligthuser/v1/**",
                "/imagedata/v1/**",
                "/event/v1/**"
        ).permitAll();
        http.authorizeRequests().antMatchers("/api/users/**", "/login/**",
                "/meneger/**",
                "/catalog/**",
                "/imagecatalog/**",
                "/imagecatalog/**",
                "/imagedata/**",
                "/kompleks/**",
                "/make/**",
                "/news/**",
                "/job/**",
                "/dom/**",
                "/orderb/**",
                "/ligthuser/**",
                "/imagedata/**",
                "/event/**").hasAnyAuthority("ADMIN");
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
