package com.armandorochin.firebaseshowcaseapp.domain

import com.armandorochin.firebaseshowcaseapp.data.network.UserService
import com.armandorochin.firebaseshowcaseapp.data.response.UserResult
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(private val userService: UserService) {
    suspend operator fun invoke(): UserResult = userService.getCurrentUserInfo()
}