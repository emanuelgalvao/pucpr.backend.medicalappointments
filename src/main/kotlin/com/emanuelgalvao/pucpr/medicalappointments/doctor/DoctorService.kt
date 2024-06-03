package com.emanuelgalvao.pucpr.medicalappointments.doctor

import com.emanuelgalvao.pucpr.medicalappointments.appointment.AppointmentRepository
import com.emanuelgalvao.pucpr.medicalappointments.doctor.model.Doctor
import com.emanuelgalvao.pucpr.medicalappointments.exceptions.constraintviolation.DoctorConstraintViolationException
import com.emanuelgalvao.pucpr.medicalappointments.exceptions.notfound.DoctorNotFoundException
import com.emanuelgalvao.pucpr.medicalappointments.exceptions.validation.DataValidationException
import com.emanuelgalvao.pucpr.medicalappointments.patient.PatientService
import com.emanuelgalvao.pucpr.medicalappointments.patient.model.Patient
import com.emanuelgalvao.pucpr.medicalappointments.util.SortDirection
import com.emanuelgalvao.pucpr.medicalappointments.util.validation.CpfValidation
import com.emanuelgalvao.pucpr.medicalappointments.util.validation.PhoneNumberValidation
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DoctorService(
    private val doctorRepository: DoctorRepository,
    private val appointmentRepository: AppointmentRepository
) {

    fun save(doctor: Doctor): Doctor {
        validateDoctorData(doctor)
        return doctorRepository.save(doctor)
    }

    fun findAll(
        sortDirection: SortDirection,
        search: String
    ): List<Doctor> {
        return if (search.isEmpty()) {
            when (sortDirection) {
                SortDirection.ASC -> doctorRepository.findAll(Sort.by("name").ascending())
                SortDirection.DESC -> doctorRepository.findAll(Sort.by("name").descending())
            }
        } else {
            val result = doctorRepository.findAllBySearch(search = search.lowercase())
            when (sortDirection) {
                SortDirection.ASC -> result.sortedBy { it.name }
                SortDirection.DESC -> result.sortedByDescending { it.name }
            }
        }
    }

    fun findById(id: Long): Doctor =
        doctorRepository.findByIdOrNull(id) ?: throw DoctorNotFoundException()

    fun remove(id: Long) {
        doctorRepository.findByIdOrNull(id)?.let {
            val appointments = appointmentRepository.findByDoctor(it)
            if (appointments.isNotEmpty()) {
                throw DoctorConstraintViolationException()
            }
            doctorRepository.deleteById(id)
        } ?: throw DoctorNotFoundException()
    }

    fun update(id: Long, doctor: Doctor): Doctor {
        validateDoctorData(doctor)
        return doctorRepository.findByIdOrNull(id)?.let {
            doctor.id = id
            doctorRepository.save(doctor)
        } ?: throw DoctorNotFoundException()
    }

    private fun validateDoctorData(doctor: Doctor) {
        if (!PhoneNumberValidation.isValidPhoneNumber(doctor.phoneNumber)) {
            log.warn("Invalid doctor phone number. Phone Number = {}", doctor.phoneNumber)
            throw DataValidationException()
        }
    }

    companion object {
        val log = LoggerFactory.getLogger(DoctorService::class.java)
    }
}