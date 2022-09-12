package com.ani.hiltapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            replace(R.id.fragmentContainerView, FriendsFragment())
        }
    }
}

@AndroidEntryPoint
class FriendsFragment : Fragment() {

    private val viewModel by activityViewModels<FriendsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.viewModelScope.launch {

        }

        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

}

@HiltViewModel
class FriendsViewModel
@Inject
constructor (
    private val local : LocalRepo,
    private val remote : RemoteRepo
) : ViewModel()

class LocalRepo

class RemoteRepo