package com.giftfunding.osds.data.repository.remote.datasource.dto.login.mapper

import com.giftfunding.osds.data.repository.remote.datasource.dto.login.LoginJwtResponseDto
import com.giftfunding.osds.domain.login.dto.LoginJwtDto

internal fun LoginJwtResponseDto.toDomain() = LoginJwtDto(
        accessToken = accessToken ?: ""
)