package com.emanuelgalvao.pucpr.medicalappointments.doctor.model

data class DoctorResponse(
    val id: Long,
    var name: String,
    var speciality: String,
    var email: String,
    var phoneNumber: String
)