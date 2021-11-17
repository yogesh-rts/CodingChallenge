package com.androidprojects.personal.codingchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidprojects.personal.codingchallenge.adapter.StoryAdapter
import com.androidprojects.personal.codingchallenge.databinding.ActivityStoryPageBinding
import com.androidprojects.personal.codingchallenge.util.Resource
import com.androidprojects.personal.codingchallenge.data.UserStory
import com.androidprojects.personal.codingchallenge.viewmodel.StoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoryPageActivity : AppCompatActivity() {

    private val viewModel: StoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingComponent = ActivityStoryPageBinding.inflate(layoutInflater)
        setContentView(bindingComponent.root)

        val storyAdapter = StoryAdapter()
        bindingComponent.apply {
            recyclerView.apply {
                adapter = storyAdapter
                layoutManager = LinearLayoutManager(this@StoryPageActivity)

            }

                            viewModel.stories.observe(this@StoryPageActivity){
                            storyAdapter.submitList(it.data?.userStory)

                            progressBar.isVisible = it is Resource.Loading && it.data?.userStory.isNullOrEmpty()
                            errorMessage.isVisible =  it is Resource.Error && it.data?.userStory.isNullOrEmpty()
                            errorMessage.text = it.error?.localizedMessage

                            Toast.makeText(applicationContext,"Your Stories has been saved and view it in offline",Toast.LENGTH_LONG).show()

                        }
            }

        }
}