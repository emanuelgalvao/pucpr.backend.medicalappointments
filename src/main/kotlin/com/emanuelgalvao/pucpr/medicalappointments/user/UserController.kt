package com.emanuelgalvao.pucpr.medicalappointments.user

import com.emanuelgalvao.pucpr.medicalappointments.user.model.LoginRequest
import com.emanuelgalvao.pucpr.medicalappointments.user.model.LoginResponse
import com.emanuelgalvao.pucpr.medicalappointments.user.model.UserRequest
import com.emanuelgalvao.pucpr.medicalappointments.user.model.UserResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    @SecurityRequirement(name = "JWT")
    fun save(@RequestBody @Valid userRequest: UserRequest): ResponseEntity<UserResponse> {
        val response = userService.save(userRequest.toUser()).toResponse()
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping
    @SecurityRequirement(name = "JWT")
    fun findAll(): ResponseEntity<List<UserResponse>> {
        val response = userService.findAll()
        return ResponseEntity.ok(
            response.map {
                it.toResponse()
            }
        )
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> =
        ResponseEntity.ok(
            userService.login(loginRequest.username.orEmpty(), loginRequest.password.orEmpty())
        )
}