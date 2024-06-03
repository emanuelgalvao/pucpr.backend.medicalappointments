package com.emanuelgalvao.pucpr.medicalappointments.appointment

import com.emanuelgalvao.pucpr.medicalappointments.appointment.model.Appointment
import com.emanuelgalvao.pucpr.medicalappointments.appointment.model.AppointmentRequest
import com.emanuelgalvao.pucpr.medicalappointments.appointment.model.AppointmentResponse
import com.emanuelgalvao.pucpr.medicalappointments.doctor.DoctorService
import com.emanuelgalvao.pucpr.medicalappointments.patient.PatientService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/appointments")
@SecurityRequirement(name = "JWT")
class AppointmentController(
    private val appointmentService: AppointmentService,
    private val patientService: PatientService,
    private val doctorService: DoctorService
) {

    @PostMapping
    fun save(@RequestBody @Valid appointmentRequest: AppointmentRequest): ResponseEntity<AppointmentResponse> {
        val appointment = Appointment(
            date = appointmentRequest.date,
            description = appointmentRequest.description,
            patient = patientService.findById(appointmentRequest.patientId),
            doctor = doctorService.findById(appointmentRequest.doctorId)
        )
        return ResponseEntity(appointmentService.save(appointment).toResponse(), HttpStatus.CREATED)
    }

    @GetMapping
    fun findAll(@RequestParam startDate: LocalDate?, @RequestParam endDate: LocalDate?): List<AppointmentResponse> =
        appointmentService.findAll(startDate, endDate).map { it.toResponse() }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<AppointmentResponse> =
        ResponseEntity.ok(appointmentService.findById(id).toResponse())

    @DeleteMapping("/{id}")
    fun removeById(@PathVariable("id") id: Long): ResponseEntity<Nothing> {
        appointmentService.remove(id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody @Valid appointmentRequest: AppointmentRequest): ResponseEntity<AppointmentResponse> {
        val appointment = Appointment(
            date = appointmentRequest.date,
            description = appointmentRequest.description,
            patient = patientService.findById(appointmentRequest.patientId),
            doctor = doctorService.findById(appointmentRequest.doctorId)
        )
        return ResponseEntity(appointmentService.update(id, appointment).toResponse(), HttpStatus.CREATED)
    }
}