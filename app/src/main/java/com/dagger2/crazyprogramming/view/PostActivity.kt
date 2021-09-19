package com.dagger2.crazyprogramming.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dagger2.crazyprogramming.R
import com.dagger2.crazyprogramming.enumType.UserIDType
import com.dagger2.crazyprogramming.viewModel.PostViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_post.*
import javax.inject.Inject

class PostActivity : AppCompatActivity() {
    @Inject
    lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_post)

        btnPost.setOnClickListener {
            getUserPostById()
        }
    }

    private fun getUserPostById() {
        var userId = 2
        when(userId) {
            1 -> UserIDType.ONE
            2 -> UserIDType.TWO
            3 -> UserIDType.THREE
            4 -> UserIDType.FOUR
            5 -> UserIDType.FIVE
            else -> UserIDType.NONE
        }

        postViewModel.getViewModelPostById(userId).observe(this@PostActivity, Observer {
            Log.d("PostIdValue", it.title)
        })
    }
}