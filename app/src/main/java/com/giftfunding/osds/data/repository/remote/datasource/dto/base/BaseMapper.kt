package com.giftfunding.osds.data.repository.remote.datasource.dto.base

import com.giftfunding.osds.domain.base.dto.UnitDto
import retrofit2.Response

internal fun Response<Unit>.toDomain() = UnitDto(
    code = this.code(),
    message = this.message()
)