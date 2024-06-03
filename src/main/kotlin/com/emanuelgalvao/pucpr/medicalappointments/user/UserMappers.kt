package com.emanuelgalvao.pucpr.medicalappointments.user

import com.emanuelgalvao.pucpr.medicalappointments.user.model.User
import com.emanuelgalvao.pucpr.medicalappointments.user.model.UserRequest
import com.emanuelgalvao.pucpr.medicalappointments.user.model.UserResponse

fun UserRequest.toUser() = User(
    name = this.name,
    username = this.username,
    password = this.password,
    role = this.role.toUserRole()
)

fun User.toResponse() = UserResponse(
    id = this.id ?: 0L,
    name = this.name,
    username = this.username,
    role = this.role.name
)