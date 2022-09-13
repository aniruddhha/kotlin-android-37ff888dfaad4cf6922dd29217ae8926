package com.ani.testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {

    @Mock
    lateinit var viewModel: LoginViewModel

    @Mock
    lateinit var sts : LiveData<String>

    @Mock
    lateinit var observer : Observer<in String>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        viewModel = spy(LoginViewModel())
        sts = viewModel.sts
    }

    @Test
    fun testViewModelNotNull() {
        assertNotNull(viewModel)
    }

    @Test
    fun testLoginFlow() {
        viewModel.sts.observeForever(observer)
        viewModel.onEm("abc")
        viewModel.onPs("abc")
        viewModel.onLgn()
        verify(observer).onChanged("success")
    }
}