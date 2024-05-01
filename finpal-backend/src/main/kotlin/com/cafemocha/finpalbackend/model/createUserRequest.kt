package com.cafemocha.finpalbackend.model

data class CreateUserRequest(
    val email: String,
    val password: String
) { }