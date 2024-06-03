package com.emanuelgalvao.pucpr.medicalappointments.patient

import com.emanuelgalvao.pucpr.medicalappointments.patient.model.PatientRequest
import com.emanuelgalvao.pucpr.medicalappointments.patient.model.PatientResponse
import com.emanuelgalvao.pucpr.medicalappointments.util.toSortDirection
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/patients")
@SecurityRequirement(name = "JWT")
class PatientController(
    private val patientService: PatientService
) {

    @PostMapping
    fun save(@RequestBody @Valid patientRequest: PatientRequest): ResponseEntity<PatientResponse> {
        val response = patientService.save(patientRequest.toPatient()).toResponse()
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping
    fun findAll(@RequestParam sort: String = "asc", @RequestParam search: String = ""): ResponseEntity<List<PatientResponse>> {
        val sortDirection = sort.toSortDirection()
        return if (sortDirection == null) {
            ResponseEntity.badRequest().build()
        } else {
            ResponseEntity.ok(
                patientService.findAll(
                    sortDirection = sortDirection,
                    search = search
                ).map {
                    it.toResponse()
                }
            )
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<PatientResponse> {
        return ResponseEntity.ok(patientService.findById(id).toResponse())
    }

    @DeleteMapping("/{id}")
    fun removeById(@PathVariable("id") id: Long): ResponseEntity<Nothing> {
        patientService.remove(id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody @Valid patientRequest: PatientRequest): ResponseEntity<PatientResponse> =
        ResponseEntity.ok(patientService.update(id, patientRequest.toPatient()).toResponse())
}