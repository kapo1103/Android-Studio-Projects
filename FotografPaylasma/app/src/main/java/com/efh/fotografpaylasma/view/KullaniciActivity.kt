package com.efh.fotografpaylasma.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.efh.fotografpaylasma.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class KullaniciActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val guncelKullanici = auth.currentUser // kullanıcı giriş yapmışsa kayıt olma ekranını açma direk haberler activity aç
        if (guncelKullanici !=null) {
            val intent = Intent(this, HaberlerActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun girisYap(view: View){

        val email = emailText.text.toString()
        val password = passwordText.text.toString()

        if (email.equals("") || password.equals("")) { // .equals("") yerine isNotEmpty da kullanılabilir
            Toast.makeText(this, "Enter email and password!", Toast.LENGTH_SHORT).show()
        } else {
            auth.signInWithEmailAndPassword(emailText.text.toString(), passwordText.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val guncelKullanici =
                            auth.currentUser?.email.toString() //kullanıcıya hoşgeldin mesajı
                        Toast.makeText(this, "Hoşgeldin: ${guncelKullanici}", Toast.LENGTH_LONG)
                            .show()

                        val intent = Intent(this, HaberlerActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }.addOnFailureListener { exception ->
                Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun kayitOl(view: View) {
        val email = emailText.text.toString()
        val password = passwordText.text.toString()

        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Enter email and password!", Toast.LENGTH_SHORT).show()
        } else {
            
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task -> // email ve şifre ile giriş yapma
//asekron çalışır işlem tamamlanınca çalış demek(addOnCompleteListener)
                    if (task.isSuccessful) {

                        val intent = Intent(this, HaberlerActivity::class.java)
                        startActivity(intent)
                        finish() // activity kapatır
                    }
                }.addOnFailureListener { exception ->
                Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}