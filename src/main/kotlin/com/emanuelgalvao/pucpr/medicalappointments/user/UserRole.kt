package com.emanuelgalvao.pucpr.medicalappointments.user

enum class UserRole {
    USER,
    ADMIN
}

fun String.toUserRole(): UserRole {
    return when(this.lowercase()) {
        "admin" -> UserRole.ADMIN
        else -> UserRole.USER
    }
}