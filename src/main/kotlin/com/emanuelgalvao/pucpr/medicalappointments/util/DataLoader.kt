package com.emanuelgalvao.pucpr.medicalappointments.util

import com.emanuelgalvao.pucpr.medicalappointments.doctor.DoctorRepository
import com.emanuelgalvao.pucpr.medicalappointments.doctor.model.Doctor
import com.emanuelgalvao.pucpr.medicalappointments.patient.PatientRepository
import com.emanuelgalvao.pucpr.medicalappointments.patient.model.Patient
import com.emanuelgalvao.pucpr.medicalappointments.user.UserRepository
import com.emanuelgalvao.pucpr.medicalappointments.user.UserRole
import com.emanuelgalvao.pucpr.medicalappointments.user.model.User
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Month

@Component
class DataLoader(
    private val patientRepository: PatientRepository,
    private val doctorRepository: DoctorRepository,
    private val userRepository: UserRepository
): CommandLineRunner {
    override fun run(vararg args: String?) {
        getInitialPatients().forEach {
            patientRepository.save(it)
        }
        getInitialDoctors().forEach {
            doctorRepository.save(it)
        }
        getInitialUsers().forEach {
            userRepository.save(it)
        }
    }

    private fun getInitialPatients(): List<Patient> = arrayListOf(
        Patient(
            name = "Yuri Benício Francisco Silva",
            dateOfBirth = LocalDate.of(1999, Month.JANUARY, 7),
            cpf = "46982598438",
            email = "yuribeniciosilva@fredericodiniz.com",
            phoneNumber = "84988269017"
        ),
        Patient(
            name = "Bruno Henry Peixoto",
            dateOfBirth = LocalDate.of(1984, Month.MARCH, 22),
            cpf = "04364298107",
            email = "bruno.henry.peixoto@gmnail.com",
            phoneNumber = "62982025589"
        ),
        Patient(
            name = "Nicolas Vicente Cavalcanti",
            dateOfBirth = LocalDate.of(1955, Month.AUGUST, 1),
            cpf = "46674195200",
            email = "nicolas-cavalcanti96@carreira.com.br",
            phoneNumber = "69986485529"
        )
    )

    private fun getInitialDoctors(): List<Doctor> = arrayListOf(
        Doctor(
            name = "Francisco Mateus Daniel Brito",
            speciality = "Pediatria",
            email = "franciscomateusbrito@helpvale.com.br",
            phoneNumber = "71983158964"
        ),
        Doctor(
            name = "Vitor Paulo Lucca Porto",
            speciality = "Oftalmologia",
            email = "vitor.paulo.porto@gmail.com",
            phoneNumber = "65991398851"
        ),
        Doctor(
            name = "Guilherme Theo da Costa",
            speciality = "Ortopedia",
            email = "guilherme_theo_dacosta@hotmail.com.br",
            phoneNumber = "95983763747"
        )
    )

    private fun getInitialUsers(): List<User> = arrayListOf(
        User(
            name = "Usuário Normal",
            username = "user",
            password = "123",
            role = UserRole.USER
        ),
        User(
            name = "Administrador",
            username = "admin",
            password = "admin",
            role = UserRole.ADMIN
        )
    )
}