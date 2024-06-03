package com.emanuelgalvao.pucpr.medicalappointments

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class MedicalAppointmentsApplication

fun main(args: Array<String>) {
	runApplication<MedicalAppointmentsApplication>(*args)
}
