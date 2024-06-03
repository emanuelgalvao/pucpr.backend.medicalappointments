package com.emanuelgalvao.pucpr.medicalappointments.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties("security")
data class SecurityProperties @ConstructorBinding constructor(
    val secretKey: String,
    val issuer: String,
    val expireHours: Long
)
