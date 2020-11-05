package com.electro.bikeapp.services

import com.electro.bikeapp.domains.EmployeeDomain
import com.electro.bikeapp.dtos.LoginDTO
import com.electro.bikeapp.repositories.AccountRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Slf4j
@Service
class LoginService implements UserDetailsService{

    @Autowired
    AccountRepository accountRepository

    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        Optional<EmployeeDomain> user = accountRepository.findByUsername(userName)

        user.orElseThrow({ -> new UsernameNotFoundException("Not found: " + userName) })

        LoginDTO loginDTO = new LoginDTO()

        List<SimpleGrantedAuthority> authorities = new ArrayList<>()
        authorities.add(new SimpleGrantedAuthority(user.get().privilegeLevel))


        loginDTO.userName = user.get().username
        loginDTO.password = user.get().encrypted_password
        loginDTO.active = true
        loginDTO.authorities = authorities
        return loginDTO

    }

}
