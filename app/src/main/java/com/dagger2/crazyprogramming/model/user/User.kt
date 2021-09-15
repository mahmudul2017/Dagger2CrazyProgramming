package com.dagger2.crazyprogramming.model.user

import com.dagger2.crazyprogramming.model.user.Address
import com.dagger2.crazyprogramming.model.user.Company

data class User(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)