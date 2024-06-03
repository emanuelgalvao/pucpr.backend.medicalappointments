package com.emanuelgalvao.pucpr.medicalappointments.doctor.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class DoctorRequest(
    @field:NotBlank
    var name: String,
    @field:NotBlank
    var speciality: String,
    @field:Email
    var email: String,
    @field:NotBlank
    var phoneNumber: String
)