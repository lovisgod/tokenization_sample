package com.lovisgod.tokenization.Helpers

class console {
    companion object {

        fun log(tag: String = "", message: String) {
            if (tag.isNullOrEmpty()) println(message) else println("$tag :::::::: $message")
        }
    }
}