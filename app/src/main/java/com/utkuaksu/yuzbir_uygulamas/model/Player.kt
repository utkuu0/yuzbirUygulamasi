package com.utkuaksu.yuzbir_uygulamas.model

data class Player(
    val name: String,
    var totalScore: Int = 0,
    var teamId: Int? = null
)
