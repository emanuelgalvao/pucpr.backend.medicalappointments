package com.emanuelgalvao.pucpr.medicalappointments.exceptions

import com.emanuelgalvao.pucpr.medicalappointments.exceptions.authentication.UserAuthenticationException
import com.emanuelgalvao.pucpr.medicalappointments.exceptions.constraintviolation.ConstraintViolationException
import com.emanuelgalvao.pucpr.medicalappointments.exceptions.notfound.NotFoundException
import com.emanuelgalvao.pucpr.medicalappointments.exceptions.validation.DataValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

@ControllerAdvice
class ExceptionHandlers {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException)
        = ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(exception: ConstraintViolationException)
        = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)

    @ExceptionHandler(UserAuthenticationException::class)
    fun handleUserAuthenticationException(exception: UserAuthenticationException)
        = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.message)

    @ExceptionHandler(DataValidationException::class)
    fun handleDataValidationException(exception: DataValidationException)
        = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttMessageNotReadableException(exception: HttpMessageNotReadableException)
        = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DataValidationException().message)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException)
        = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DataValidationException().message)
}