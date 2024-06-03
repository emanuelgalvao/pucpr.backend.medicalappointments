package com.emanuelgalvao.pucpr.medicalappointments.doctor

import com.emanuelgalvao.pucpr.medicalappointments.doctor.model.Doctor
import com.emanuelgalvao.pucpr.medicalappointments.doctor.model.DoctorRequest
import com.emanuelgalvao.pucpr.medicalappointments.doctor.model.DoctorResponse

fun DoctorRequest.toDoctor() = Doctor(
    name = this.name,
    speciality = this.speciality,
    email = this.email,
    phoneNumber = this.phoneNumber
)

fun Doctor.toResponse() = DoctorResponse(
    id = this.id ?: 0L,
    name = this.name,
    speciality = this.speciality,
    email = this.email,
    phoneNumber = this.phoneNumber
)