package com.benbaha.fria


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.benbaha.fria.R
import com.benbaha.fria.Utils
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }*/
        setContentView(R.layout.activity_login)
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setTitle("Fria")
       // val buttonLogin: View = findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener{
            logInUser()
        }
    }

    private fun logInUser() {
        val email: String = inputEmail.getText().toString()
        val password: String = inputPassword.getText().toString()
        if (email.isEmpty()) {
            inputEmail.setError(getString(com.benbaha.fria.R.string.email_required))
            inputEmail.requestFocus()
        }
        if (Validation.isValidEmail(email)) {
            inputEmail.setError(getString(R.string.enter_a_valid_email_address))
            inputEmail.requestFocus()
            return
        }
        if (password.isEmpty()) {
            inputPassword.setError(getString(R.string.password_required))
            inputPassword.requestFocus()
            return
        }
        if (!Validation.isValidPassword(password)) {
            inputPassword.setError(getString(R.string.password__at_least_8_characters))
            inputPassword.requestFocus()
            return
        }
        if (!Validation.isValidCredentials(email,password)) {
            inputPassword.setError(getString(R.string.Invalid_email_or_password))
            inputPassword.requestFocus()
            return
        }
        saveToken("my_token_value")
        val intent = Intent(this, OpenCameraActivity::class.java)
        startActivity(intent)
        finish()
        //val alert: AlertDialog = createAlertDialog(this)

    }
    private fun saveToken(token: String) {
        val editor = getSharedPreferences("my_app", Context.MODE_PRIVATE).edit()
        editor.putString(Utils.TOKEN, token)
        editor.putLong(Utils.EXPIRY_TIME.toString(), System.currentTimeMillis() + Utils.EXPIRY_TIME)
        editor.apply()
    }
}
object Validation {
    private const val PASSWORD_MIN_LENGTH = 8
    private const val NAME_MIN_LENGTH = 3
    fun isValidEmail(email: String?): Boolean {
        return !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= PASSWORD_MIN_LENGTH
    }

    fun isValidName(name: String): Boolean {
        return name.length >= NAME_MIN_LENGTH
    }
    fun isValidCredentials(email: String, password: String): Boolean {
        val validCredentials = mapOf(
            "a@gmail.com" to "12345678",
            "showroom1@gmail.com" to "showRoomDZ2001",
            "showroom2@gmail.com" to "showRoomDZ2002",
            "showroom3@gmail.com" to "showRoomDZ2003",
            "showroom4@gmail.com" to "showRoomDZ2004",
            "showroom5@gmail.com" to "showRoomDZ2005",
            "showroom6@gmail.com" to "showRoomDZ2006",
            "showroom7@gmail.com" to "showRoomDZ2007",
            "showroom8@gmail.com" to "showRoomDZ2008",
            "showroom9@gmail.com" to "showRoomDZ2009",
            "showroom10@gmail.com" to "showRoomDZ2010"
        )
        return validCredentials[email] == password
    }
}
object ProgressDialog {
    fun createAlertDialog(activity: Activity): AlertDialog {
        val inflater = activity.layoutInflater
        val dialogLayout: View = inflater.inflate(R.layout.custom_progress_dialog, null)
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogLayout)
        val alert = builder.create()
        alert.show()
        alert.window!!.setLayout(600, 300)
        return alert
    }
}