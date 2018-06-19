package com.park9eon.micro.book.controller

import com.park9eon.micro.book.model.User
import com.park9eon.micro.book.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/book")
open class BookController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/user/{userId}")
    open fun getUser(@PathVariable userId: Long, principal: Principal?): Any? {
        return principal
    }
}