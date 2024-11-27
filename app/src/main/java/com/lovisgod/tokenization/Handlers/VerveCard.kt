package com.lovisgod.tokenization.Handlers

object VerveCard {

    fun selectPPSEApplication(commad: ByteArray): ByteArray {
        // Handle APDU commands here
        // Example response: SW1-SW2: 0x9000 (Success)
        return byteArrayOf(0x90.toByte(), 0x00.toByte())
    }

    fun selectApplication(): ByteArray {

        // Handle APDU commands here
        // Example response: SW1-SW2: 0x9000 (Success)
        return byteArrayOf(0x90.toByte(), 0x00.toByte())
    }
}