package com.emanuelgalvao.pucpr.medicalappointments.patient.model

import java.time.LocalDate

data class PatientResponse(
    val id: Long,
    val name: String,
    val dateOfBirth: LocalDate?,
    val cpf: String,
    val email: String,
    val phoneNumber: String
)
