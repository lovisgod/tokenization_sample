package com.lovisgod.tokenization

import com.lovisgod.tokenization.Handlers.PaybleCard
import com.lovisgod.tokenization.Handlers.VerveCard
import com.lovisgod.tokenization.Helpers.Conversions
import com.lovisgod.tokenization.Helpers.HexUtil
import com.lovisgod.tokenization.Helpers.console
import com.lovisgod.tokenization.Helpers.domain.CardType
import com.lovisgod.tokenization.Helpers.domain.SELECTEDCARDTYPE
import java.util.Arrays

object HceHelper {


    fun handleAPduCommand(command: ByteArray) : ByteArray {

        HexUtil.toHexString(command)?.let { console.log("Command coming in", it) }

        // we are handling for just verve for now
        val selectPPSECommand = HexUtil.parseHex("00A404000E325041592E5359532E444446303100")
        val selectApplicationForpaybleCardCommand = HexUtil.parseHex("00A4040008D276000150415942")
        when {
            Arrays.equals(selectPPSECommand, command) -> {
                console.log("check for ppse", "it is ppse command")
                return when (SELECTEDCARDTYPE) {
                    CardType.VERVE -> VerveCard.selectPPSEApplication(command)
                    CardType.MASTER -> ByteArray(0) // handle this later
                    CardType.VISA -> ByteArray(0) // handle this later
                    CardType.PAYBLE -> PaybleCard.selectPPSEApplication(command)
                    else -> byteArrayOf(0x6D.toByte(), 0x00.toByte())
                }
            }

            Arrays.equals(selectApplicationForpaybleCardCommand, command) -> {
                console.log("select application", "Payble select application")
                return PaybleCard.selectApplication()
            }
            else -> {
                // Handle the case where the command is not selectPPSECommand
                byteArrayOf(0x6D.toByte(), 0x00.toByte())
            }
        }



        // return unknown command
        return byteArrayOf(0x6D.toByte(), 0x00.toByte())
    }
}