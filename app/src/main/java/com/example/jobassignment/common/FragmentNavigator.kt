package com.example.jobassignment.common

import com.example.jobassignment.R

enum class FragmentNavigator(val navigateActionId: Int) {
    OPEN_HOME_SCREEN_FROM_ON_BOARD_SCREEN(R.id.action_onBoardScreenFragment_to_homeScreenFragment),
    OPEN_ON_BOARD_SCREEN_FROM_HOME_SCREEN(R.id.action_homeScreenFragment_to_onBoardScreenFragment)
}