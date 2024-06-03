package com.emanuelgalvao.pucpr.medicalappointments.exceptions.notfound

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
open class NotFoundException(
    message: String = HttpStatus.NOT_FOUND.reasonPhrase
): Exception(message)