package kom.example.loginapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kom.example.loginapi.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tokenResultText.text = "Token: " + intent.getStringExtra("token")
    }
}