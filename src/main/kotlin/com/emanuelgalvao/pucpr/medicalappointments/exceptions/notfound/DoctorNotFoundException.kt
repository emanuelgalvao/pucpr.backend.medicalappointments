package com.emanuelgalvao.pucpr.medicalappointments.exceptions.notfound

class DoctorNotFoundException(
    message: String = "Médico não encontrado."
): NotFoundException(message)