package com.emanuelgalvao.pucpr.medicalappointments.exceptions.notfound

class AppointmentNotFoundException(
    message: String = "Consulta médica não encontrada."
): NotFoundException(message)