package com.emanuelgalvao.pucpr.medicalappointments.user.model

data class LoginResponse(
    val token: String,
    val user: UserResponse
)
