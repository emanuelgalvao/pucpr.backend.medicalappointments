package com.emanuelgalvao.pucpr.medicalappointments.doctor

import com.emanuelgalvao.pucpr.medicalappointments.doctor.model.Doctor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DoctorRepository: JpaRepository<Doctor, Long> {

    @Query("SELECT * FROM doctors d WHERE LOWER(d.name) LIKE %:search%", nativeQuery = true)
    fun findAllBySearch(@Param("search") search: String): List<Doctor>
}