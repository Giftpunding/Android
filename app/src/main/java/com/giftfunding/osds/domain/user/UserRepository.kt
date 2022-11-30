package com.giftfunding.osds.domain.user

import com.giftfunding.osds.data.repository.remote.datasource.dto.user.UserInfoDto

interface UserRepository {
    fun getUserInfo() : UserInfoDto
}