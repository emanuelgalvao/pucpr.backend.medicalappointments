package com.emanuelgalvao.pucpr.medicalappointments.appointment

import com.emanuelgalvao.pucpr.medicalappointments.appointment.model.Appointment
import com.emanuelgalvao.pucpr.medicalappointments.appointment.model.AppointmentResponse
import com.emanuelgalvao.pucpr.medicalappointments.doctor.toResponse
import com.emanuelgalvao.pucpr.medicalappointments.patient.toResponse

fun Appointment.toResponse() = AppointmentResponse(
    id = this.id ?: 0L,
    date = this.date,
    description = this.description,
    patient = this.patient.toResponse(),
    doctor = this.doctor.toResponse()
)