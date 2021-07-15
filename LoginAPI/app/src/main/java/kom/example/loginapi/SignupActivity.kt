package kom.example.loginapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kom.example.loginapi.databinding.ActivitySignupBinding
import kom.example.loginapi.restapi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.signupFinshButton.setOnClickListener {
            signup(
                binding.usernameInput.text.toString(),
                binding.passwordInput.text.toString(),
                binding.emailInput.text.toString(),
                binding.nameInput.text.toString(),
                binding.phoneInput.text.toString()
            )
        }

        binding.toLoginButton.setOnClickListener {
            finish()
        }
    }

    private fun signup(username: String, password: String, email: String, name: String, phone: String) {
        var accountSignupRequestDTO = AccountSignupRequestDTO(username, password, email, name, phone, null)

        RetrofitBuilder.accountApi.signupRequest(accountSignupRequestDTO).enqueue(object:
            Callback<AccountSignupResponseDTO> {
            override fun onResponse(
                call: Call<AccountSignupResponseDTO>,
                response: Response<AccountSignupResponseDTO>
            ) {
                if(response.isSuccessful) {
                    setResult(RESULT_OK)
                }
                else {
                    setResult(RESULT_CANCELED)
                }

                finish();
            }

            override fun onFailure(call: Call<AccountSignupResponseDTO>, t: Throwable) {
                Log.d("error", t.message.toString())
            }
        })
    }
}