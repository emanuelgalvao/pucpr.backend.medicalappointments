package com.emanuelgalvao.pucpr.medicalappointments.util.validation

import br.com.colman.simplecpfvalidator.isCpf

class CpfValidation {

    companion object {

        fun isValidCpf(cpf: String): Boolean {
            return cpf.isCpf()
        }
    }
}