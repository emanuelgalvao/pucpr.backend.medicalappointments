package com.emanuelgalvao.pucpr.medicalappointments.exceptions.constraintviolation

class DoctorConstraintViolationException(
    message: String = "Médico está vinculado a uma consulta."
): ConstraintViolationException(message)