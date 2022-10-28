package com.zoho.people.data.common

data class ResponseItems<T>(
    val results: List<T>
)