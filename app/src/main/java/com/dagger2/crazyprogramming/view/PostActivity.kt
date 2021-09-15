package com.dagger2.crazyprogramming.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dagger2.crazyprogramming.R
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
        postViewModel.getViewModelPostById(2).observe(this@PostActivity, Observer {
            Log.d("PostIdValue", it.title)
        })
    }
}