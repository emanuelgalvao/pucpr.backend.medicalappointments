package com.emanuelgalvao.pucpr.medicalappointments.patient.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

@Entity
@Table(name = "patients")
class Patient(
    @Id
    @GeneratedValue
    var id: Long? = null,
    @NotNull
    var name: String = "",
    var dateOfBirth: LocalDate? = null,
    @Column(unique = true, nullable = false)
    var cpf: String = "",
    @NotNull
    var email: String = "",
    @NotNull
    var phoneNumber: String = ""
)