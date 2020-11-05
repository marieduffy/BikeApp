package com.electro.bikeapp.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.stereotype.Controller

@Controller
class WebController {

    @GetMapping("/")
    String home() {
        return "/home"
    }

    @GetMapping("/login")
    String login() {
        return "/login"
    }

    @GetMapping("/user")
    String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    String admin() {
        return "/contact"
    }

    @GetMapping("/about")
    String about() {
        return "/about"
    }

    @GetMapping("/contact")
    String contact() {
        return "/contact"
    }

    @GetMapping("/owner")
    String owner() {
        return "/owner"
    }
}