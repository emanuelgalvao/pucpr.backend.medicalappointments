package com.emanuelgalvao.pucpr.medicalappointments.exceptions.constraintviolation

class PatientConstraintViolationException(
    message: String = "Paciente está vinculado a uma consulta."
): ConstraintViolationException(message)