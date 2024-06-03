package com.emanuelgalvao.pucpr.medicalappointments.exceptions.validation

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ResponseStatus(HttpStatus.BAD_REQUEST)
open class DataValidationException(
    message: String = "Verifique os dados enviados."
): Exception(message)