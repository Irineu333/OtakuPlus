package com.neo.otaku.util.extensions

fun String.firstSubstring(regex: Regex): String? {
    return regex.find(this)?.value
}