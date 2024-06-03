package com.emanuelgalvao.pucpr.medicalappointments.doctor

import com.emanuelgalvao.pucpr.medicalappointments.doctor.model.DoctorRequest
import com.emanuelgalvao.pucpr.medicalappointments.doctor.model.DoctorResponse
import com.emanuelgalvao.pucpr.medicalappointments.util.toSortDirection
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/doctors")
@SecurityRequirement(name = "JWT")
class DoctorController(
    private val doctorService: DoctorService
) {

    @PostMapping
    fun save(@RequestBody @Valid doctorRequest: DoctorRequest): ResponseEntity<DoctorResponse> {
        val response = doctorService.save(doctorRequest.toDoctor()).toResponse()
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping
    fun findAll(@RequestParam sort: String = "asc", @RequestParam search: String = ""): ResponseEntity<List<DoctorResponse>> {
        val sortDirection = sort.toSortDirection()
        return if (sortDirection == null) {
            ResponseEntity.badRequest().build()
        } else {
            ResponseEntity.ok(
                doctorService.findAll(
                    sortDirection = sortDirection,
                    search = search
                ).map {
                    it.toResponse()
                }
            )
        }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<DoctorResponse> =
        ResponseEntity.ok(doctorService.findById(id).toResponse())

    @DeleteMapping("/{id}")
    fun removeById(@PathVariable("id") id: Long): ResponseEntity<Nothing> {
        doctorService.remove(id)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody @Valid doctorRequest: DoctorRequest): ResponseEntity<DoctorResponse> =
        ResponseEntity.ok(doctorService.update(id, doctorRequest.toDoctor()).toResponse())
}