package com.zoho.people.models.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RemoteUserEntity(
    @SerializedName("name") val name: Name,
    @SerializedName("email") val email: String,
    @SerializedName("picture") val picture: Picture
) : Serializable {
    data class Name(
        @SerializedName("first") val first: String,
        @SerializedName("last") val last: String
    ) : Serializable

    data class Picture(
        @SerializedName("large") val large: String,
        @SerializedName("thumbnail") val thumbnail: String,
    ) : Serializable
}