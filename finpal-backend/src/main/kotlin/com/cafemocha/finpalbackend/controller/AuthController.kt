package com.cafemocha.finpalbackend.controller

import com.cafemocha.finpalbackend.model.CreateUserRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val firebaseAuth: FirebaseAuth) {
    @PostMapping("/createUser")
    fun createUser(@RequestBody userDetails: CreateUserRequest): UserRecord {
        val request = UserRecord.CreateRequest()
            .setEmail(userDetails.email ?: throw IllegalArgumentException("Email is required"))
            .setPassword(userDetails.password ?: throw IllegalArgumentException("Password is required"))
            .setDisabled(false)
        return firebaseAuth.createUser(request)
    }

    @GetMapping("/getUser")
    fun getUser(@RequestBody userId: String): UserRecord {
        return firebaseAuth.getUser(userId)
    }
}