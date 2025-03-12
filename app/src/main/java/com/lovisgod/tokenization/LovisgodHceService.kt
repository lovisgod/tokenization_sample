package com.lovisgod.tokenization

import android.nfc.cardemulation.HostApduService
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.lovisgod.tokenization.Helpers.HexUtil
import com.lovisgod.tokenization.Helpers.console

class LovisgodHceService: HostApduService() {

    companion object {
        var isHceEnabled = false;
    }

    override fun processCommandApdu(p0: ByteArray?, p1: Bundle?): ByteArray {

        if(!isHceEnabled) return ByteArray(0)

        Log.d("MyHostApduService", "Received APDU")
        console.log("got gere for process command", "${p0?.let { HexUtil.toHexString(it) }}")
        if (p0 != null) {
            var response  = HceHelper.handleAPduCommand(p0)
            if (response.isNotEmpty()) {
                showToast("Apdu command processed")
                return  response
            } else {
                // instruction not supported
                return byteArrayOf(0x6D.toByte(), 0x00.toByte())
            }
        }

        // instruction not supported
        return byteArrayOf(0x6D.toByte(), 0x00.toByte())
    }

    override fun onDeactivated(p0: Int) {
        isHceEnabled = false
       console.log("CARD DISCONNECTED", "${p0}")
    }

    private fun showToast(message: String) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
        }
    }
}