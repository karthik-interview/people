package com.zoho.people.models.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RemoteUserEntity(
    @SerializedName("name") val name: Name,
    @SerializedName("email") val email: String,
    @SerializedName("picture") val picture: Picture,
    @SerializedName("phone") val phone: String, //
    @SerializedName("location") val location: Location,
    @SerializedName("dob") val dob: DateInfo,
) : Serializable {
    data class Name(
        @SerializedName("first") val first: String,
        @SerializedName("last") val last: String
    ) : Serializable

    data class Picture(
        @SerializedName("large") val large: String,
        @SerializedName("thumbnail") val thumbnail: String,
    ) : Serializable

    data class Location(
        @SerializedName("street") val street: Street,
        @SerializedName("city") val city: String,
        @SerializedName("state") val state: String,
        @SerializedName("country") val country: String,
        @SerializedName("postcode") val postcode: String,
        @SerializedName("coordinates") val coordinates: Coordinates,
    ) : Serializable {
        data class Street(
            @SerializedName("name") val name: String,
            @SerializedName("number") val number: String,
        ) : Serializable

        data class Coordinates(
            @SerializedName("latitude") val latitude: String,
            @SerializedName("longitude") val longitude: String,
        ) : Serializable
    }

    data class DateInfo(
        @SerializedName("date") val date: String,
        @SerializedName("age") val age: Int,
    )
}