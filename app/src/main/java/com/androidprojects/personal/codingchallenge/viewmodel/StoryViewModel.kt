package com.androidprojects.personal.codingchallenge.viewmodel

import androidx.lifecycle.*
import com.androidprojects.personal.codingchallenge.api.ApiRequest
import com.androidprojects.personal.codingchallenge.data.Story
import com.androidprojects.personal.codingchallenge.repository.StoryRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(
    repoStory: StoryRepo
) : ViewModel() {
    val stories = repoStory.getStoriesList().asLiveData()
    


}