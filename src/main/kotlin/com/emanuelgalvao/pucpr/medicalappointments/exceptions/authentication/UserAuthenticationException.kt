package com.emanuelgalvao.pucpr.medicalappointments.exceptions.authentication

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ResponseStatus(HttpStatus.UNAUTHORIZED)
open class UserAuthenticationException(
    message: String = "Usuário e/ou senha incorretos."
): Exception(message)