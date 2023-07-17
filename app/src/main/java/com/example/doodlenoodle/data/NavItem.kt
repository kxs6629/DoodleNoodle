package com.example.doodlenoodle.data

import androidx.compose.ui.graphics.vector.ImageVector

//Data class that represents a navigation item in the nav menu

data class NavItem(
    val id: String,
    val title: String,
    val contentDescription: String,
    val icon: ImageVector
)