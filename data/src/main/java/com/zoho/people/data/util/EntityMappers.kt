package com.zoho.people.data.util

import com.zoho.people.core.model.data.User
import com.zoho.people.data.local.LocalUserEntity
import com.zoho.people.data.remote.RemoteUserEntity

fun User.toLocalUserEntity() = LocalUserEntity(
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

fun LocalUserEntity.toUserEntity() = User(
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


fun RemoteUserEntity.toDomainUserEntity() = User(
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