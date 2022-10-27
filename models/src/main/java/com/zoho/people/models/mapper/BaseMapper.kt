package com.zoho.people.models.mapper

interface BaseMapper<E, P> {

    fun toPresenter(entity: E): P
}