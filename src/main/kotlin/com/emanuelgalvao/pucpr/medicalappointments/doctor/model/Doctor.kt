package com.emanuelgalvao.pucpr.medicalappointments.doctor.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "doctors")
class Doctor(
    @Id
    @GeneratedValue
    var id: Long? = null,
    @NotNull
    var name: String = "",
    @NotNull
    var speciality: String = "",
    @Column(unique = true, nullable = false)
    var email: String = "",
    @Column(unique = true, nullable = false)
    var phoneNumber: String = ""
)