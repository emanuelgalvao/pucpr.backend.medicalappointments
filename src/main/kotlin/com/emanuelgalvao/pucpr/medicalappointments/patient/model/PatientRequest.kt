package com.emanuelgalvao.pucpr.medicalappointments.patient.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

data class PatientRequest(
    @field:NotBlank
    val name: String,
    val dateOfBirth: LocalDate,
    @field:NotBlank
    val cpf: String,
    @field:Email
    val email: String,
    @field:NotBlank
    val phoneNumber: String
)