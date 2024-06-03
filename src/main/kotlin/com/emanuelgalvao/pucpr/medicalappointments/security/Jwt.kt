package com.emanuelgalvao.pucpr.medicalappointments.security

import com.emanuelgalvao.pucpr.medicalappointments.user.model.User
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.jackson.io.JacksonDeserializer
import io.jsonwebtoken.jackson.io.JacksonSerializer
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.Date

@Component
class Jwt(
    private val properties: SecurityProperties
) {

    fun createToken(user: User): String =
        UserToken(user).let {
            Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(properties.secretKey.toByteArray()))
                .serializeToJsonWith(JacksonSerializer())
                .setIssuedAt(utcNow().toDate())
                .setExpiration(utcNow().plusHours(properties.expireHours).toDate())
                .setIssuer(properties.issuer)
                .setSubject(user.id.toString())
                .claim(USER_FIELD, it)
                .compact()
        }

    fun extract(req: HttpServletRequest): Authentication? {
        try {
            val header = req.getHeader(AUTHORIZATION)
            if (header.isNullOrEmpty() || !header.startsWith("Bearer")) return null
            val token = header.replace("Bearer", "").trim()
            val claims = Jwts
                .parserBuilder()
                .setSigningKey(properties.secretKey.toByteArray())
                .deserializeJsonWith(JacksonDeserializer<MutableMap<String, *>?>(mapOf(USER_FIELD to UserToken::class.java)))
                .build()
                .parseClaimsJws(token)
                .body
            if (claims.issuer != properties.issuer) return null
            val user = claims.get(USER_FIELD, UserToken::class.java)
            return user.toAuthentication()
        } catch (e: Throwable) {
            log.debug("Token rejected", e)
            return null
        }
    }

    companion object {
        val log = LoggerFactory.getLogger(Jwt::class.java)
        const val USER_FIELD = "user"

        private fun utcNow() = ZonedDateTime.now(ZoneOffset.UTC)
        private fun ZonedDateTime.toDate(): Date = Date.from(this.toInstant())

        private fun UserToken.toAuthentication(): Authentication {
            val authorities = listOf(SimpleGrantedAuthority("ROLE_$role"))
            return UsernamePasswordAuthenticationToken.authenticated(this, id, authorities)
        }
    }
}