package com.emanuelgalvao.pucpr.medicalappointments.patient

import com.emanuelgalvao.pucpr.medicalappointments.patient.model.Patient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PatientRepository: JpaRepository<Patient, Long> {

    @Query("SELECT * FROM patients p WHERE LOWER(p.name) LIKE %:search% OR p.cpf LIKE %:search%", nativeQuery = true)
    fun findAllBySearch(@Param("search") search: String): List<Patient>
}