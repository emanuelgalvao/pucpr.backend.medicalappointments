package com.emanuelgalvao.pucpr.medicalappointments.appointment.model

import com.emanuelgalvao.pucpr.medicalappointments.doctor.model.Doctor
import com.emanuelgalvao.pucpr.medicalappointments.patient.model.Patient
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Entity
@Table(name = "appointments")
class Appointment(
    @Id
    @GeneratedValue
    var id: Long? = null,
    @NotNull
    var date: LocalDateTime = LocalDateTime.now(),
    var description: String = "",
    @ManyToOne
    @JoinColumn(name = "patient_id")
    var patient: Patient,
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    var doctor: Doctor
)