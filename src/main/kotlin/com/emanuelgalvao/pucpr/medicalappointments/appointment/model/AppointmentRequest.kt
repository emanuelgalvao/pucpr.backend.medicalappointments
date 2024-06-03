package com.emanuelgalvao.pucpr.medicalappointments.appointment.model

import jakarta.validation.constraints.NotNull
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class AppointmentRequest(
    @field:DateTimeFormat
    val date: LocalDateTime,
    val description: String,
    @field:NotNull
    val patientId: Long,
    @field:NotNull
    val doctorId: Long
)
