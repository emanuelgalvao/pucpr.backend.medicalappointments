package com.emanuelgalvao.pucpr.medicalappointments.patient

import com.emanuelgalvao.pucpr.medicalappointments.patient.model.Patient
import com.emanuelgalvao.pucpr.medicalappointments.patient.model.PatientRequest
import com.emanuelgalvao.pucpr.medicalappointments.patient.model.PatientResponse

fun PatientRequest.toPatient(): Patient = Patient(
    name = this.name,
    dateOfBirth = this.dateOfBirth,
    cpf = this.cpf,
    email = this.email,
    phoneNumber = this.phoneNumber
)

fun Patient.toResponse(): PatientResponse = PatientResponse(
    id = this.id ?: 0L,
    name = this.name,
    dateOfBirth = this.dateOfBirth,
    cpf = this.cpf,
    email = this.email,
    phoneNumber = this.phoneNumber
)