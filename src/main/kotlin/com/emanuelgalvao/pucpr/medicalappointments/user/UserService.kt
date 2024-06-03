package com.emanuelgalvao.pucpr.medicalappointments.user

import com.emanuelgalvao.pucpr.medicalappointments.exceptions.authentication.UserAuthenticationException
import com.emanuelgalvao.pucpr.medicalappointments.security.Jwt
import com.emanuelgalvao.pucpr.medicalappointments.user.model.LoginResponse
import com.emanuelgalvao.pucpr.medicalappointments.user.model.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    val jwt: Jwt
) {

    fun save(user: User): User =
        userRepository.save(user)

    fun findAll(): List<User> =
        userRepository.findAll()

    fun login(username: String, password: String): LoginResponse {
        val user = userRepository.findByUsername(username).firstOrNull()
        user?.let {
            if (password != it.password) {
                log.warn("Invalid password for user {}.", username)
                throw UserAuthenticationException()
            }
            log.info("User {} logged in.", username)
            return LoginResponse(
                token = jwt.createToken(it),
                user = it.toResponse()
            )
        } ?: run {
            log.warn("User {} not found.", username)
            throw UserAuthenticationException()
        }
    }

    companion object {
        val log = LoggerFactory.getLogger(UserService::class.java)
    }
}