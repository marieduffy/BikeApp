package com.electro.bikeapp

import com.electro.bikeapp.repositories.AccountRepository
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AccountRepository)
class BikeappApplication {

static void main(String[] args) {
	SpringApplication.run(BikeappApplication, args)
}

}
