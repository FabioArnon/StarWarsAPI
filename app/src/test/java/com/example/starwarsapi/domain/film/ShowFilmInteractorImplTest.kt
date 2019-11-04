package com.example.starwarsapi.domain.film

import com.example.starwarsapi.models.film.FilmResponse
import com.example.starwarsapi.presentation.base.DispatcherProvider
import com.example.starwarsapi.repository.film.ShowFilmRepository
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.utils.DispatcherProviderTest
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ShowFilmInteractorImplTest {
    lateinit var showFilmInteractorImpl: ShowFilmInteractorImpl
    @Mock
    lateinit var repository: ShowFilmRepository
    @Mock
    lateinit var filmResponse: FilmResponse

    val dispatcherProvider: DispatcherProvider = DispatcherProviderTest()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        showFilmInteractorImpl = ShowFilmInteractorImpl(
            repository,
            dispatcherProvider,
            CoroutineScope(Dispatchers.Unconfined)
        )
    }

    @Test
    fun `@GetListFilm - Retorna com SUCESSO um Result de FilmResponse ao receber pagina VALIDA e search VAZIO`() {
        runBlocking {
            `when`(repository.getListFilm(1, "")).thenReturn(Result.Success(filmResponse))
            showFilmInteractorImpl.getListFilm(1,""){
                assertTrue(it is Result.Success)
            }
            verify(repository).getListFilm(1,"")
        }
    }

    @Test
    fun `@GetListFilm - Retorna com SUCESSO um Result de FilmResponse ao receber pagina VALIDA e search NAO VAZIO`() {
        runBlocking {
            `when`(repository.getListFilm(-1, "lu")).thenReturn(Result.Failure(Throwable()))
            showFilmInteractorImpl.getListFilm(-1,"lu"){
                assertTrue(it is Result.Failure)
            }
            verify(repository).getListFilm(-1,"lu")
        }
    }
}