package com.utkuaksu.yuzbir_uygulamas.model

data class GameSettings(
    val isTeamMode: Boolean = false,
    val teamNames: List<String>? = null,
    val playerCount: Int = 0 // varsa başka parametreler de buraya eklenebilir
)
