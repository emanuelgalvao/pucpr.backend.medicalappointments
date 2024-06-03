package com.emanuelgalvao.pucpr.medicalappointments.appointment

import com.emanuelgalvao.pucpr.medicalappointments.appointment.model.Appointment
import com.emanuelgalvao.pucpr.medicalappointments.doctor.model.Doctor
import com.emanuelgalvao.pucpr.medicalappointments.patient.model.Patient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface AppointmentRepository: JpaRepository<Appointment, Long> {

    @Query("SELECT * FROM appointments a WHERE a.date BETWEEN :startDate AND :endDate", nativeQuery = true)
    fun findAllByDates(@Param("startDate") startDate: LocalDate, @Param("endDate") endDate: LocalDate): List<Appointment>

    @Query("SELECT * FROM appointments a ORDER BY a.date", nativeQuery = true)
    fun findAllOrderByDate(): List<Appointment>

    fun findByPatient(patient: Patient): List<Appointment>

    fun findByDoctor(doctor: Doctor): List<Appointment>
}