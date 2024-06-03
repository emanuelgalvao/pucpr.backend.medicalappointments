package com.emanuelgalvao.pucpr.medicalappointments.user

import com.emanuelgalvao.pucpr.medicalappointments.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByUsername(username: String): List<User>
}