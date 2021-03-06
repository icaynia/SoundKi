package com.icaynia.pracler.data;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by icaynia on 13/02/2017.
 *
 *
 * firebase에 계정 추가하고
 * database와 연동하는 것.
 */

public class AccountManager
{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public AccountManager()
    {
        mAuth = FirebaseAuth.getInstance();
    }

    public void createUser(String email, String password, OnCompleteListener listener)
    {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    }

}
