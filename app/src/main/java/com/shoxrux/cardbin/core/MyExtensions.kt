package com.shoxrux.cardbin.core

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.shoxrux.cardbin.R

fun NavController.changeScreen(navDirections: NavDirections) {

    val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.alpha_in)
        .setExitAnim(R.anim.alpha_out)
        .setPopEnterAnim(R.anim.alpha_pop_in)
        .setPopExitAnim(R.anim.alpha_pop_out)
        .build()

    navigate(navDirections, navOptions)
}