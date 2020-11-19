package com.electro.bikeapp.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.stereotype.Controller

@Controller
@SuppressWarnings(['UnnecessaryReturnKeyword'])
class WebController {

    @GetMapping('/')
    String home() {
        return '/home'
    }

    @GetMapping('/login')
    String login() {
        return '/login'
    }

    @GetMapping('/user')
    String user() {
        return '/user'
    }

    @GetMapping('/admin')
    String admin() {
        return '/contact'
    }

    @GetMapping('/about')
    String about() {
        return '/about'
    }

    @GetMapping('/contact')
    String contact() {
        return '/contact'
    }

    @GetMapping('/owner')
    String owner() {
        return '/owner'
    }

    @GetMapping('/manager')
    String manager() {
        return '/manager'
    }

    @GetMapping('/vendorOrder')
    String vendorOrder() {
        return '/vendorOrder'
    }

    @GetMapping('/vendorHomePage')
    String vendorHomePage() {
        return '/vendorHomePage'
    }

    @GetMapping('/vendorOrdersCurrent')
    String vendorOrdersCurrent() {
        return '/vendorOrdersCurrent'
    }

    @GetMapping('/inventory')
    String inventory() {
        return '/inventory'
    }

    @GetMapping('/createEmployee')
    String createEmployee() {
        return '/createEmployee'
    }

}
