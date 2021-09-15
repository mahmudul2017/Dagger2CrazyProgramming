package com.dagger2.crazyprogramming.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dagger2.crazyprogramming.R
import com.dagger2.crazyprogramming.adapter.DiUserAdapter
import com.dagger2.crazyprogramming.model.user.User
import com.dagger2.crazyprogramming.utils.showSuccessToast
import com.dagger2.crazyprogramming.viewModel.DiViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_dagger2.*
import javax.inject.Inject

class DiActivity: AppCompatActivity() {
    @Inject
    lateinit var diViewModel: DiViewModel

    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_dagger2)

        getUserList()

        btnUserPost.setOnClickListener {
            startActivity(Intent(this@DiActivity, PostActivity::class.java))
        }
    }

    private fun getUserList() {
        diViewModel.getViewModelUser().observe(this, Observer { loggedUser ->
            when (loggedUser) {
                else -> {
                    if (loggedUser != null) {
                        diViewModel.apiCallStatus.postValue("SUCCESS")
                        val userAdapter = DiUserAdapter(loggedUser as ArrayList<User>,
                            object : DiUserAdapter.OnItemClickListener {
                                override fun onItemClick(user: User?) {
                                    showSuccessToast(
                                        this@DiActivity,
                                         "${user?.name}"
                                    )
                                }
                            })
                        userRecycler.layoutManager = LinearLayoutManager(this)
                        userRecycler.adapter = userAdapter
                        Log.d("userData", loggedUser.toString())
                    } else {
                        diViewModel.apiCallStatus.postValue("NO-DATA")
                        Log.d("userNoData", loggedUser.toString())
                    }
                }
            }
        })
    }
}