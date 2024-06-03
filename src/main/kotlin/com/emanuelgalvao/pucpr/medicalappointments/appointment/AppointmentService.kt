package com.emanuelgalvao.pucpr.medicalappointments.appointment

import com.emanuelgalvao.pucpr.medicalappointments.appointment.model.Appointment
import com.emanuelgalvao.pucpr.medicalappointments.exceptions.notfound.AppointmentNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class AppointmentService(
    private val appointmentRepository: AppointmentRepository
) {

    fun save(appointment: Appointment): Appointment =
        appointmentRepository.save(appointment)

    fun findAll(startDate: LocalDate?, endDate: LocalDate?): List<Appointment> {
        return if (startDate == null || endDate == null) {
            appointmentRepository.findAllOrderByDate()
        } else {
            appointmentRepository.findAllByDates(startDate, endDate)
        }
    }

    fun findById(id: Long): Appointment =
        appointmentRepository.findByIdOrNull(id) ?: throw AppointmentNotFoundException()

    fun remove(id: Long) {
        appointmentRepository.findByIdOrNull(id)?.let {
            appointmentRepository.deleteById(id)
        }  ?: throw AppointmentNotFoundException()
    }

    fun update(id: Long, appointment: Appointment): Appointment {
        return appointmentRepository.findByIdOrNull(id)?.let {
            appointment.id = id
            appointmentRepository.save(appointment)
        } ?: throw AppointmentNotFoundException()
    }
}