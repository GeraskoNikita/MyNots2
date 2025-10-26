package com.example.mynots2.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mynots2.R
import com.example.mynots2.data.local.Pref
import com.example.mynots2.databinding.FragmentAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var pref: Pref
    private lateinit var signInOption: GoogleSignInClient

    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account.idToken)
                } catch (e: ApiException) {
                    updateUi(null)
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        pref = Pref(layoutInflater.context)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(
            getString(R.string.default_web_client_id)
        ).requestEmail().build()
        signInOption = GoogleSignIn.getClient(requireContext(), gso)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnLogin.setOnClickListener {
            signInLauncher.launch(signInOption.signInIntent)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener  {task ->
            if (task.isSuccessful){
                val user = auth.currentUser
                updateUi(user)
            }else{
                updateUi(null)
            }
        }
    }

    private fun updateUi(user: FirebaseUser?) {
        if (user != null){
            findNavController().navigate(R.id.MainFragment)
            pref.saveUserAuth(true)
        }else{
            Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_SHORT).show()
        }

    }


}




