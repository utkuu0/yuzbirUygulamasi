package com.utkuaksu.yuzbir_uygulamas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.utkuaksu.yuzbir_uygulamas.databinding.ActivityMainBinding
import com.utkuaksu.yuzbir_uygulamas.ui.WelcomeFragment
import com.utkuaksu.yuzbir_uygulamas.ui.setup.fragment.PlayerSetupFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}