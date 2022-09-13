package com.ani.testing.batch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {

    @Mock
    lateinit var viewModel: LoginViewModel

    @Mock
    lateinit var sts : LiveData<String>

    @Mock
    lateinit var observer : Observer<String>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        viewModel = Mockito.spy(LoginViewModel())
        sts = viewModel.sts
    }

    @Test
    fun testViewModelNotNull() {
        Assert.assertNotNull(viewModel)
    }

    @Test
    fun testStsObserver() {
        viewModel.sts.observeForever(observer)

        viewModel.setEml("abc")
        viewModel.setPas("abc")

        viewModel.onLogin()

        Mockito.verify(observer).onChanged("fail")
    }
}