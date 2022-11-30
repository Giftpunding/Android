package com.giftfunding.osds.data.repository.remote.datasource.dto

interface DataToDomainMapper<T> {
    fun toDomainModel() : T
}