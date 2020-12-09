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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
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

    @Bean
    AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new UrlAuthenticationSuccessHandler()
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
                    .antMatchers('/about').permitAll()
                    .antMatchers('/contact').permitAll()
                    .antMatchers('/').permitAll()
        //OWNER PAGES
                    .antMatchers('/bikeRepair').hasAuthority('OWNER')
                    .antMatchers('/employeeHome').hasAuthority('OWNER')
                    .antMatchers('/help').hasAuthority('OWNER')
                    .antMatchers('/displayInventory').hasAuthority('OWNER')
                    .antMatchers('/owner').hasAuthority('OWNER')
                    .antMatchers('/ownerContact').hasAuthority('OWNER')
                    .antMatchers('/payroll').hasAnyAuthority('OWNER')
                    .antMatchers('/salesDiscounts').hasAuthority('OWNER')
                    .antMatchers('/vendorHomePage').hasAuthority('OWNER')
                    .antMatchers('/displayInventory').hasAuthority('OWNER')
        //BOOKKEEPER PAGES
                    .antMatchers('/bookkeeper').hasAnyAuthority('BOOKKEEPER', 'OWNER')
                    .antMatchers('/employeeInfo').hasAnyAuthority('BOOKKEEPER', 'OWNER')
                    .antMatchers('/bookkeeperContact').hasAnyAuthority('BOOKKEEPER', 'OWNER')
                    .antMatchers('/bookkeeperHelp').hasAnyAuthority('BOOKKEEPER', 'OWNER')
                    .antMatchers('/bookkeeperPayroll').hasAnyAuthority('BOOKKEEPER', 'OWNER')
        //EMPLOYEE PAGES
                    .antMatchers('/employee').authenticated()
                    .antMatchers('/employeeBikeRepair').authenticated()
                    .antMatchers('/employeeContact').authenticated()
                    .antMatchers('/employeeHelp').authenticated()
                    .antMatchers('/employeeDisplayInventory').authenticated()
                    .antMatchers('/employeeVendorHomePage').authenticated()
        //MANAGER PAGES
                    .antMatchers('/manager').hasAnyAuthority('MANAGER', 'OWNER')
                    .antMatchers('/managerBikeRepair').hasAnyAuthority('MANAGER', 'OWNER')
                    .antMatchers('/managerContact').hasAnyAuthority('MANAGER', 'OWNER')
                    .antMatchers('/managerEmployeeHome').hasAnyAuthority('MANAGER', 'OWNER')
                    .antMatchers('/managerHelp').hasAnyAuthority('MANAGER', 'OWNER')
                    .antMatchers('/managerDisplayInventory').hasAnyAuthority('MANAGER', 'OWNER')
                    .antMatchers('/managerSalesDiscounts').hasAnyAuthority('MANAGER', 'OWNER')
                    .antMatchers('/managerVendorHomePage').hasAnyAuthority('MANAGER', 'OWNER')
        //OTHER PAGES
                    .antMatchers('/changeVOStatus').authenticated()
                    .antMatchers('/createEmployee').authenticated()
                    .antMatchers('/createRetailOrder').authenticated()
                    .antMatchers('/inventory').authenticated()
                    .antMatchers('/requestTime').authenticated()
                    .antMatchers('/updateEmployee').authenticated()
                    .antMatchers('/vendorOrder').authenticated()
                    .antMatchers('/vendorOrdersCurrent').authenticated()
                .and()
                .formLogin()
                        .loginPage('/login')
                        .permitAll()
                        .successHandler(myAuthenticationSuccessHandler())

                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher('/logout'))
                    .logoutSuccessUrl('/login')
    }

}
