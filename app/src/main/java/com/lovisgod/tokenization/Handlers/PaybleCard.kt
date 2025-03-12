package com.lovisgod.tokenization.Handlers

import com.lovisgod.tokenization.Helpers.HexUtil
import com.lovisgod.tokenization.Helpers.console

object PaybleCard {

    fun selectPPSEApplication(commad: ByteArray): ByteArray {
        console.log("payble ppse", "payble ppse")
        // Handle APDU commands here

        /***
         * Breakdown of the Response
         * 6F 23 → FCI Template (TLV response structure)
         * 840E325041592E5359532E4444463031 → DF Name (2PAY.SYS.DDF01)
         * A5 11 → FCI Proprietary Template
         * BF 0C 0E → FCI Issuer Discretionary Data
         * 61 0C → Application Template
         * 4F 08 D2 76 00 01 50 41 59 42 → Your AID (D276000150415942)
         * 87 01 01 → Priority Indicator (1)
         * 90 00 → Success status word***/
        val ppseResponse = HexUtil.parseHex("6F23840E325041592E5359532E4444463031A511BF0C0E610C4F08D2760001504159428701019000")
        return ppseResponse
    }


    fun selectApplication(): ByteArray {
        /*
        *Tag	Length	Value	Description
        6F 2C	44 (0x2C) bytes	File Control Information (FCI) Template
        84 08	8 bytes	D276000150415942	Dedicated File (DF) Name (AID: Payble)
        A5 20	32 (0x20) bytes	FCI Proprietary Template
        50 0D	13 bytes	504159424C452057414C4C4554	Application Label: "PAYBLE WALLET"
        87 01	1 byte	01	Application Priority Indicator (Higher priority = lower value)
        9F38 03	3 bytes	5F2A 02	Processing Options Data Object List (PDOL) (Defines required data)
        BF0C 05	5 bytes	Issuer Discretionary Data (Custom data for issuer)
        5F2D 02	2 bytes	656E	Language Preference (en = English)
        9000	Status Word	Success Response
        * **/
        // respond to select application
        val selectAppResponse = HexUtil.parseHex("6F2C8408D276000150415942A520500D504159424C452057414C4C45548701019F38035F2A02BF0C055F2D02656E9000")
        return selectAppResponse
    }
}