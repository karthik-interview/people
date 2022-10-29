package com.zoho.people.data.util

import com.zoho.people.data.local.LocalUserEntity
import com.zoho.people.data.remote.RemoteUserEntity
import dev.thedukerchip.domain.models.UserEntity

fun UserEntity.toLocalUserEntity() = LocalUserEntity(
    email = email,
    firstName = firstName,
    lastName = lastName,
    profileUri = profileUri,
    profileThumbnailUri = profileThumbnailUri,
    phone = phone,
    dob = dob,
    age = age,
    streetName = streetName,
    streetNo = streetNo,
    city = city,
    state = state,
    country = country,
    postcode = postcode,
    latitude = latitude,
    longitude = longitude,
)

fun LocalUserEntity.toUserEntity() = UserEntity(
    email = email,
    firstName = firstName,
    lastName = lastName,
    profileUri = profileUri,
    profileThumbnailUri = profileThumbnailUri,
    phone = phone,
    dob = dob,
    age = age,
    streetName = streetName,
    streetNo = streetNo,
    city = city,
    state = state,
    country = country,
    postcode = postcode,
    latitude = latitude,
    longitude = longitude,
)


fun RemoteUserEntity.toDomainUserEntity() = UserEntity(
    firstName = name.first,
    lastName = name.last,
    email = email,
    profileUri = picture.large,
    profileThumbnailUri = picture.thumbnail,
    phone = phone,
    dob = dob.date,
    age = dob.age,
    streetName = location.street.name,
    streetNo = location.street.number,
    city = location.city,
    state = location.state,
    country = location.country,
    postcode = location.postcode,
    latitude = location.coordinates.latitude,
    longitude = location.coordinates.longitude
)