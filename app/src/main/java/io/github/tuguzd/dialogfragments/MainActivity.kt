package io.github.tuguzd.dialogfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.tuguzd.dialogfragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fragmentContainer.setOnClickListener {
            TestCheckboxDialog().show(supportFragmentManager, TestCheckboxDialog::class.simpleName)
        }
    }
}
