package com.lovisgod.tokenization.Helpers

interface IBerTlvLogger {
    val isDebugEnabled: Boolean
    fun debug(aFormat: String?, vararg args: Any?)
}