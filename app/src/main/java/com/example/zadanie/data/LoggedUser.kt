package com.example.zadanie.data

class LoggedUser(private var accId: Long, private var pin: String) {

    fun setAccId(accId: Long) {
        this.accId = accId
    }
    fun setPin(pin: String) {
        this.pin = pin
    }
    fun getAccId() : Long {
        return this.accId
    }
    fun getPin() : String {
        return this.pin
    }
}  