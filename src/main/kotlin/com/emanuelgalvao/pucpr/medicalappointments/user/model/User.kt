package com.emanuelgalvao.pucpr.medicalappointments.user.model

import com.emanuelgalvao.pucpr.medicalappointments.user.UserRole
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue
    var id: Long? = null,
    @NotNull
    var name: String = "",
    @Column(unique = true, nullable = false)
    var username: String = "",
    @NotNull
    var password: String = "",
    @NotNull
    var role: UserRole
)