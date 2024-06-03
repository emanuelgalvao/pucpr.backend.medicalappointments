package com.emanuelgalvao.pucpr.medicalappointments.exceptions.constraintviolation

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ResponseStatus(HttpStatus.BAD_REQUEST)
open class ConstraintViolationException(
    message: String = HttpStatus.BAD_REQUEST.reasonPhrase
): Exception(message)