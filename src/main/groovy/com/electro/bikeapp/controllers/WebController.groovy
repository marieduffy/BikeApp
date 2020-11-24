package com.electro.bikeapp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@SuppressWarnings(['UnnecessaryReturnKeyword', 'DuplicateStringLiteral'])
class WebController {

    @RequestMapping('/')
    String home() {
        return '/home'
    }

    @RequestMapping('/login')
    String login() {
        return '/login'
    }

    @RequestMapping('/user')
    String user() {
        return '/user'
    }

    @RequestMapping('/admin')
    String admin() {
        return '/contact'
    }

    @RequestMapping('/about')
    String about() {
        return '/about'
    }

    @RequestMapping('/contact')
    String contact() {
        return '/contact'
    }

    @RequestMapping('/owner')
    String owner() {
        return '/owner'
    }

    @RequestMapping('/manager')
    String manager() {
        return '/manager'
    }

    @RequestMapping('/vendorOrder')
    String vendorOrder() {
        return '/vendorOrder'
    }

    @RequestMapping('/vendorHomePage')
    String vendorHomePage() {
        return '/vendorHomePage'
    }

    @RequestMapping('/vendorOrdersCurrent')
    String vendorOrdersCurrent() {
        return '/vendorOrdersCurrent'
    }

    @RequestMapping('/inventory')
    String inventory() {
        return '/inventory'
    }

    @RequestMapping('/createEmployee')
    String createEmployee() {
        return '/createEmployee'
    }

    @RequestMapping('/createRetailOrder')
    String createRetailOrder() {
        return '/createRetailOrder'
    }

    @RequestMapping('/updateEmployee')
    String updateEmployee() {
        return '/updateEmployee'
    }

}
