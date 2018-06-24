package com.norman.hoeller.recyclerapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.norman.hoeller.recyclerapp.data.Resource
import com.norman.hoeller.recyclerapp.db.BookDao
import com.norman.hoeller.recyclerapp.items.Item
import com.norman.hoeller.recyclerapp.repo.LibraryRepo
import com.norman.hoeller.recyclerapp.util.mock
import com.norman.hoeller.recyclerapp.util.successData
import com.norman.hoeller.recyclerapp.viewmodel.LibraryViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class TestLibraryViewModel {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: LibraryViewModel
    lateinit var repo: LibraryRepo
    lateinit var bookDao: BookDao
    lateinit var executors: AppExecutors

    @Before
    fun setup() {
        bookDao = Mockito.mock(BookDao::class.java)
        executors = Mockito.mock(AppExecutors::class.java)
        repo = Mockito.mock(LibraryRepo(bookDao, executors)::class.java)
        viewModel = LibraryViewModel(repo)
    }

    @Test
    fun callRepo() {
        viewModel.data.observeForever(mock())
        viewModel.loadData()
        verify(repo).loadData()
    }

    @Test
    fun loadData_statusLoading() {
        val resultLoading = Resource.loading(null)
        val loading = MutableLiveData<Resource<List<Item>>>()
        `when`(repo.loadData()).thenReturn(loading)

        val observer = mock<Observer<Resource<List<Item>>>>()
        viewModel.data.observeForever(observer)
        viewModel.loadData()
        verify(observer, never()).onChanged(any())

        loading.value = resultLoading
        verify(observer).onChanged(resultLoading)
    }

    @Test
    fun loadData_statusSuccess() {
        val resultSuccess = successData()
        val loading = MutableLiveData<Resource<List<Item>>>()
        `when`(repo.loadData()).thenReturn(loading)

        val observer = mock<Observer<Resource<List<Item>>>>()
        viewModel.data.observeForever(observer)
        viewModel.loadData()
        verify(observer, never()).onChanged(any())

        loading.value = resultSuccess
        verify(observer).onChanged(resultSuccess)
    }
}