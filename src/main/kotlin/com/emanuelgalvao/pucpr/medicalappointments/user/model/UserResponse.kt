package com.emanuelgalvao.pucpr.medicalappointments.user.model

data class UserResponse(
    val id: Long,
    val name: String,
    val username: String,
    val role: String
)
