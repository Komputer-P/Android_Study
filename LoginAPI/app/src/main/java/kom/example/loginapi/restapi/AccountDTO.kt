package kom.example.loginapi.restapi

data class AccountLoginRequestDTO (
    val username: String,
    val password: String
        )

data class AccountLoginResponseDTO (
    val token: String
        )

data class AccountSignupRequestDTO (
    val username: String,
    val password: String,
    val email: String,
    val name: String,
    val phone: String,
    val photo: String?
        )

data class AccountSignupResponseDTO (
    val message: String
        )