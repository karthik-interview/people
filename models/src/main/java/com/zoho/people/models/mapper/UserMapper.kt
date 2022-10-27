package com.zoho.people.models.mapper

import com.zoho.people.models.data.RemoteUserEntity
import com.zoho.people.models.presentation.UserEntity

class UserMapper : BaseMapper<RemoteUserEntity, UserEntity> {
    override fun toPresenter(entity: RemoteUserEntity): UserEntity = UserEntity(
        firstName = entity.name.first,
        lastName = entity.name.last,
        email = entity.email,
        profileUri = entity.picture.large,
        profileThumbnailUri = entity.picture.thumbnail
    )
}
