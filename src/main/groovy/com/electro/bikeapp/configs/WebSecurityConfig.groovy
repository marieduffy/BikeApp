package com.electro.bikeapp.configs

import com.electro.bikeapp.utils.StringEncryption
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService

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
                .authorizeRequests()
                    .antMatchers("/manager").hasAnyAuthority("MANAGER", "OWNER")
                    .antMatchers("/owner").hasAuthority("OWNER")
                    .antMatchers("/user").authenticated()
                    .antMatchers("/").permitAll()
                    .antMatchers("/contact").permitAll()
                    .antMatchers("/about").permitAll()
                .and()
                .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
        // TODO: Make logout functionality
        // TODO: Add pages and set privileges
    }

//    @Bean
//    PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance()
//    }
}