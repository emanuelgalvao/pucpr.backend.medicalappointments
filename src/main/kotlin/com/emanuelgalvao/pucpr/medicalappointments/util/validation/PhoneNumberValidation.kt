package com.emanuelgalvao.pucpr.medicalappointments.util.validation

class PhoneNumberValidation {

    companion object {

        private const val PHONE_NUMBER_LENGHT = 10
        private const val CELLPHONE_NUMBER_LENGHT = 11

        fun isValidPhoneNumber(phoneNumber: String): Boolean {
            val phoneNumberOnlyNumbers = phoneNumber.replace("\\D+".toRegex(), "")
            return phoneNumberOnlyNumbers.length == PHONE_NUMBER_LENGHT ||
                    phoneNumberOnlyNumbers.length == CELLPHONE_NUMBER_LENGHT
        }
    }
}