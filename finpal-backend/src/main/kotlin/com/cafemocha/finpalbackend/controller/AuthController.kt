package com.cafemocha.finpalbackend.controller

import com.cafemocha.finpalbackend.model.CreateUserRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(private val firebaseAuth: FirebaseAuth) {
    @PostMapping("/signUp")
    fun createUser(@RequestBody userDetails: CreateUserRequest): UserRecord {
        val request = UserRecord.CreateRequest()
            .setEmail(userDetails.email ?: throw IllegalArgumentException("Email is required"))
            .setPassword(userDetails.password ?: throw IllegalArgumentException("Password is required"))
            .setDisabled(false)
        return firebaseAuth.createUser(request)
    }

    @GetMapping("/getUser")
    fun getUser(@RequestParam("uid") userId: String): ResponseEntity<Any> {
        return try {
            val userRecord: UserRecord = firebaseAuth.getUser(userId)
            ResponseEntity.ok(userRecord)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving user: ${e.message}")
        }
    }
}