package com.emanuelgalvao.pucpr.medicalappointments.user.model

import jakarta.validation.constraints.NotBlank

data class UserRequest(
    @field:NotBlank
    val name: String,
    @field:NotBlank
    val username: String,
    @field:NotBlank
    val password: String,
    @field:NotBlank
    val role: String
)
