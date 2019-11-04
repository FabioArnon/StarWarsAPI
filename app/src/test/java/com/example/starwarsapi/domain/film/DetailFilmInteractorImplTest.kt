package com.example.starwarsapi.domain.film

import com.example.starwarsapi.models.people.People
import com.example.starwarsapi.models.starship.Starships
import com.example.starwarsapi.presentation.base.DispatcherProvider
import com.example.starwarsapi.repository.film.ShowFilmRepository
import com.example.starwarsapi.repository.people.ShowPeopleRepository
import com.example.starwarsapi.repository.starship.ShowStarshipRepository
import com.example.starwarsapi.service.Result
import com.example.starwarsapi.utils.DispatcherProviderTest
import junit.framework.Assert.assertEquals
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

class DetailFilmInteractorImplTest {
    lateinit var detailFilmInteractorImpl: DetailFilmInteractorImpl
    @Mock
    lateinit var repository: ShowFilmRepository
    @Mock
    lateinit var repositoryStarship: ShowStarshipRepository
    @Mock
    lateinit var repositoryPeople: ShowPeopleRepository
    @Mock
    lateinit var starships: Starships
    @Mock
    lateinit var people: People

    var dispatcherProvider: DispatcherProvider = DispatcherProviderTest()


    val list = listOf<String>("1")
    val listNula = listOf<String>("")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailFilmInteractorImpl = DetailFilmInteractorImpl(
            repository,
            repositoryStarship,
            repositoryPeople,
            dispatcherProvider,
            CoroutineScope(Dispatchers.Unconfined)
        )
    }

    @Test
    fun `@GetSStarshipId - Retorna com sucesso as starships listadas ao receber uma lista NAO NULA`() {
        runBlocking {
            `when`(repositoryStarship.getStarshipsId("1")).thenReturn(Result.Success(starships))
            detailFilmInteractorImpl.getStarshipId(list) {
                assertTrue(it is Result.Success)
            }
            verify(repositoryStarship).getStarshipsId("1")
        }
    }

    @Test
    fun `@GetSStarshipId - Retorna com falha ao receber uma lista NULA`() {
        runBlocking {
            `when`(repositoryStarship.getStarshipsId("")).thenReturn(Result.Failure(Throwable()))
            detailFilmInteractorImpl.getStarshipId(listNula) {
                assertTrue(it is Result.Failure)
            }
            verify(repositoryStarship).getStarshipsId("")
        }
    }

    @Test
    fun `@GetSStarshipId - Retorna com falha ao receber uma lista com IDS INVALIDOS`() {
        runBlocking {
            `when`(repositoryStarship.getStarshipsId("asd")).thenReturn(Result.Failure(Throwable()))
            detailFilmInteractorImpl.getStarshipId(listOf("asd")) {
                assertTrue(it is Result.Failure)
            }
            verify(repositoryStarship).getStarshipsId("asd")
        }
    }

    @Test
    fun `@getStarshipById - Retorna SUCESSO ao receber um id VALIDO`() {
        runBlocking {
            `when`(repositoryStarship.getStarshipsId("1")).thenReturn(Result.Success(starships))
            val response = detailFilmInteractorImpl.getStarshipById("1")
            verify(repositoryStarship).getStarshipsId("1")
            assertTrue(response is Starships)
        }
    }

    @Test
    fun `@getStarshipById - Retorna FALHA ao receber um id INVALIDO`() {
        runBlocking {
            `when`(repositoryStarship.getStarshipsId("asd")).thenReturn(Result.Failure(Throwable()))
            val response = detailFilmInteractorImpl.getStarshipById("asd")
            verify(repositoryStarship).getStarshipsId("asd")
            assertTrue(response == null)
        }
    }

    @Test
    fun `@GetPeopleId - Retorna SUCESSO ao receber uma lista de IDS VALIDOS`() {
        runBlocking {
            `when`(repositoryPeople.getPeopleId("1")).thenReturn(Result.Success(people))
            detailFilmInteractorImpl.getPeopleId(list) {
                assertTrue(it is Result.Success)
            }
            verify(repositoryPeople).getPeopleId("1")
        }
    }

    @Test
    fun `@GetPeopleId - Retorna FALHA ao receber uma lista de IDS NULOS`() {
        runBlocking {
            `when`(repositoryPeople.getPeopleId("")).thenReturn(
                Result.Failure(
                    Throwable()
                )
            )
            detailFilmInteractorImpl.getPeopleId(listOf("")) {
                assertTrue(it is Result.Failure)
            }
            verify(repositoryPeople).getPeopleId("")
        }
    }

    @Test
    fun `@GetPeopleId - Retorna FALHA ao receber uma lista de IDS INVALIDOS`() {
        runBlocking {
            `when`(repositoryPeople.getPeopleId("asd")).thenReturn(
                Result.Failure(
                    Throwable()
                )
            )
            detailFilmInteractorImpl.getPeopleId(listOf("asd")) {
                assertTrue(it is Result.Failure)
            }
            verify(repositoryPeople).getPeopleId("asd")
        }
    }

    @Test
    fun `@getPeopleById - Retorna SUCESSO ao receber um ID VALIDO`() {
        runBlocking {
            `when`(repositoryPeople.getPeopleId("1")).thenReturn(Result.Success(people))
            val response = detailFilmInteractorImpl.getPeopleById("1")
            verify(repositoryPeople).getPeopleId("1")
            assertTrue(response is People)
        }
    }

    @Test
    fun `@getPeopleById - Retorna FALHA ao receber um ID INVALIDO`() {
        runBlocking {
            `when`(repositoryPeople.getPeopleId("asd")).thenReturn(Result.Failure(Throwable()))
            val response = detailFilmInteractorImpl.getPeopleById("asd")
            val verifyId = verify(repositoryPeople).getPeopleId("asd")
            assertEquals(response, verifyId)
        }
    }
}
