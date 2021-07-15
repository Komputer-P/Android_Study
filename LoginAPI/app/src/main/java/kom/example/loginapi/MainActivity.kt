package kom.example.loginapi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import kom.example.loginapi.databinding.ActivityMainBinding
import kom.example.loginapi.restapi.AccountLoginRequestDTO
import kom.example.loginapi.restapi.AccountLoginResponseDTO
import kom.example.loginapi.restapi.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val singUpForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if(result.resultCode == Activity.RESULT_OK) {
            val toast = Toast.makeText(applicationContext, "회원가입 성공!", Toast.LENGTH_LONG)
            toast.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginButton.setOnClickListener {
            login(binding.usernameText.text.toString(), binding.passwordText.text.toString());
        }

        binding.signupButton.setOnClickListener {
            singUpForResult.launch(Intent(this, SignupActivity::class.java))
        }
    }

    private fun login(username: String, password: String) {
        var accountLoginRequestDTO = AccountLoginRequestDTO(username, password)

        RetrofitBuilder.accountApi.loginRequest(accountLoginRequestDTO).enqueue(object: Callback<AccountLoginResponseDTO>{
            override fun onResponse(
                call: Call<AccountLoginResponseDTO>,
                response: Response<AccountLoginResponseDTO>
            ) {
                if(response.isSuccessful) {
                    intent = Intent(applicationContext, ResultActivity::class.java);
                    intent.putExtra("token", response.body()?.token)

                    startActivity(intent)
                }
                else {
                    val toast = Toast.makeText(applicationContext, "로그인 실패!", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }

            override fun onFailure(call: Call<AccountLoginResponseDTO>, t: Throwable) {
                Log.d("error", t.message.toString())
            }
        })
    }
}