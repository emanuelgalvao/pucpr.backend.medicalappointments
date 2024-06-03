package com.emanuelgalvao.pucpr.medicalappointments.patient

import com.emanuelgalvao.pucpr.medicalappointments.appointment.AppointmentRepository
import com.emanuelgalvao.pucpr.medicalappointments.exceptions.constraintviolation.PatientConstraintViolationException
import com.emanuelgalvao.pucpr.medicalappointments.exceptions.notfound.PatientNotFoundException
import com.emanuelgalvao.pucpr.medicalappointments.exceptions.validation.DataValidationException
import com.emanuelgalvao.pucpr.medicalappointments.patient.model.Patient
import com.emanuelgalvao.pucpr.medicalappointments.util.SortDirection
import com.emanuelgalvao.pucpr.medicalappointments.util.validation.CpfValidation
import com.emanuelgalvao.pucpr.medicalappointments.util.validation.PhoneNumberValidation
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PatientService(
    private val patientRepository: PatientRepository,
    private val appointmentRepository: AppointmentRepository
) {

    fun save(patient: Patient): Patient {
        validatePatientData(patient)
        return patientRepository.save(patient)
    }

    fun findAll(
        sortDirection: SortDirection,
        search: String
    ): List<Patient> {
        return if (search.isEmpty()) {
            when (sortDirection) {
                SortDirection.ASC -> patientRepository.findAll(Sort.by("name").ascending())
                SortDirection.DESC -> patientRepository.findAll(Sort.by("name").descending())
            }
        } else {
            val result = patientRepository.findAllBySearch(search = search.lowercase())
            when (sortDirection) {
                SortDirection.ASC -> result.sortedBy { it.name }
                SortDirection.DESC -> result.sortedByDescending { it.name }
            }
        }
    }

    fun findById(id: Long): Patient =
        patientRepository.findByIdOrNull(id) ?: throw PatientNotFoundException()

    fun remove(id: Long) {
        patientRepository.findByIdOrNull(id)?.let {
            val appointments = appointmentRepository.findByPatient(it)
            if (appointments.isNotEmpty()) {
                throw PatientConstraintViolationException()
            }
            patientRepository.deleteById(id)
        } ?: throw PatientNotFoundException()
    }

    fun update(id: Long, patient: Patient): Patient {
        validatePatientData(patient)
        return patientRepository.findByIdOrNull(id)?.let {
            patient.id = id
            patientRepository.save(patient)
        } ?: throw PatientNotFoundException()
    }

    private fun validatePatientData(patient: Patient) {
        if (!CpfValidation.isValidCpf(patient.cpf)) {
            log.warn("Invalid patient CPF. CPF = {}", patient.cpf)
            throw DataValidationException()
        }
        if (!PhoneNumberValidation.isValidPhoneNumber(patient.phoneNumber)) {
            log.warn("Invalid patient phone number. Phone Number = {}", patient.phoneNumber)
            throw DataValidationException()
        }
    }

    companion object {
        val log = LoggerFactory.getLogger(PatientService::class.java)
    }
}