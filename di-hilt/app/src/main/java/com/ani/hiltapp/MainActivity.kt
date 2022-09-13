package com.ani.hiltapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var gson : Gson

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

    private val viewModel : FriendsViewModel by viewModels()

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
    private val remote : RemoteRepo,
) : ViewModel()

@ViewModelScoped
class LocalRepo
@Inject constructor()

@ViewModelScoped
class RemoteRepo
@Inject constructor()

@Module
@InstallIn(ActivityComponent::class)
class GsonConfigModule {

    @ActivityScoped
    @Provides
    fun gson()  = Gson()
}