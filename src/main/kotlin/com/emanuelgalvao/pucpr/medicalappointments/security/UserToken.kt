package com.emanuelgalvao.pucpr.medicalappointments.security

import com.emanuelgalvao.pucpr.medicalappointments.user.UserRole
import com.emanuelgalvao.pucpr.medicalappointments.user.model.User
import com.emanuelgalvao.pucpr.medicalappointments.user.toUserRole
import com.fasterxml.jackson.annotation.JsonIgnore

data class UserToken(
    val id: Long,
    val name: String,
    val role: String
) {
    constructor(): this(0L, "", "")
    constructor(user: User): this(
        id = user.id ?: 0L,
        name = user.name,
        role = user.role.name
    )

    @get:JsonIgnore
    val isAdmin: Boolean get() = role.toUserRole() == UserRole.ADMIN
}