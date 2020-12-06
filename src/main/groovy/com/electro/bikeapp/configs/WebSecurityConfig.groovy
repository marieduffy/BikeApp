package com.electro.bikeapp.configs

import com.electro.bikeapp.utils.StringEncryption
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import com.google.common.collect.ImmutableList

@Configuration
@EnableWebSecurity
@SuppressWarnings(['VariableName', 'DuplicateStringLiteral', 'UnnecessarySetter'])
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration()
        configuration.setAllowedOrigins(ImmutableList.of('*'))
        configuration.setAllowedMethods(ImmutableList.of('HEAD', 'GET', 'POST', 'PUT', 'DELETE', 'PATCH'))
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*'
        // when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true)
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(ImmutableList.of('Authorization', 'Cache-Control', 'Content-Type'))
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration('/**', configuration)
        return source
    }

    @Bean
    PasswordEncoder encoder() {
        return new StringEncryption()
    }

    @Bean
    DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService())
        authProvider.setPasswordEncoder(new StringEncryption())
        return authProvider
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers('/manager').hasAnyAuthority('MANAGER', 'OWNER')
                    .antMatchers('/owner').hasAuthority('OWNER')
                    .antMatchers('/bookkeeper').hasAnyAuthority('BOOKKEEPER', 'MANAGER', 'OWNER')
                    .antMatchers('/user').authenticated()
                    .antMatchers('/').permitAll()
                    .antMatchers('/contact').permitAll()
                    .antMatchers('/about').permitAll()
                    .antMatchers('/inventory').authenticated()
                    .antMatchers('/vendorHomePage').authenticated()

                    .antMatchers('/employeeInfo').hasAnyAuthority('BOOKKEEPER', 'MANAGER')
                    .antMatchers('/bookkeeperContact').hasAnyAuthority('BOOKKEEPER', 'MANAGER')
                    .antMatchers('/payroll').hasAnyAuthority('BOOKKEEPER', 'MANAGER')
                .and()
                .formLogin()
                        .loginPage('/login')
                        .permitAll()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher('/logout'))
                    .logoutSuccessUrl('/login')
    }

}
