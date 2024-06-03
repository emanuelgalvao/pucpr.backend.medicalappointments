package com.emanuelgalvao.pucpr.medicalappointments.appointment.model

import com.emanuelgalvao.pucpr.medicalappointments.doctor.model.DoctorResponse
import com.emanuelgalvao.pucpr.medicalappointments.patient.model.PatientResponse
import java.time.LocalDateTime

data class AppointmentResponse(
    val id: Long,
    val date: LocalDateTime,
    val description: String,
    val patient: PatientResponse,
    val doctor: DoctorResponse
)
