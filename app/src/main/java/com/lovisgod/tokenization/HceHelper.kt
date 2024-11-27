package com.lovisgod.tokenization

import com.lovisgod.tokenization.Handlers.VerveCard
import com.lovisgod.tokenization.Helpers.Conversions
import com.lovisgod.tokenization.Helpers.HexUtil
import com.lovisgod.tokenization.Helpers.console
import java.util.Arrays

object HceHelper {

    fun handleAPduCommand(command: ByteArray) : ByteArray {

        HexUtil.toHexString(command)?.let { console.log("Command coming in", it) }

        // we are handling for just verve for now
        val selectPPSECommand = HexUtil.parseHex(HexUtil.convertStringToHex("2PAY.SYS.DDF01"))
        if (Arrays.equals(selectPPSECommand, command)) {
           return VerveCard.selectPPSEApplication(command)
        }


        // return unknown command
        return byteArrayOf(0x6D.toByte(), 0x00.toByte())
    }
}