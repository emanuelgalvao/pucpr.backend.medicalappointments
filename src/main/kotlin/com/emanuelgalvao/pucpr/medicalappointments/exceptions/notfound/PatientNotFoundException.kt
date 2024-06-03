package com.emanuelgalvao.pucpr.medicalappointments.exceptions.notfound

class PatientNotFoundException(
    message: String = "Paciente não encontrado."
): NotFoundException(message)