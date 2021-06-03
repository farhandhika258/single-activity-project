package com.example.myapplication


import com.example.myapplication.domain.Announcer
import com.example.myapplication.domain.Navigator
import com.example.myapplication.services.CryptoApiBuilder
import com.example.myapplication.services.CryptoReceiver
import com.example.myapplication.services.CryptoService
import com.example.myapplication.ui.dashboard.DashboardViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DashboardTest {

    @RelaxedMockK
    lateinit var dashboardViewModel: DashboardViewModel

    @RelaxedMockK
    lateinit var navigator: Navigator

    @RelaxedMockK
    lateinit var announcer: Announcer

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dashboardViewModel = DashboardViewModel(CryptoService(CryptoApiBuilder()), navigator, announcer)
    }

    @Test
    fun `test fetchData with valid result`() {
        dashboardViewModel.loadResults()

        val captureData = slot<CryptoReceiver>()

        verify(exactly = 1) {  }
        captureData.captured.let { res ->
            assertNotNull(res)
            assert(res.data.isNotEmpty())
            assertEquals("Value 1", res.data.first().coinInfo?.name)
        }
    }

    @Test
    fun `Test fetchData with an exception`() {
        every { dashboardViewModel.loadResults() } throws IllegalStateException("Error Occurred")
        dashboardViewModel.loadResults()

        verify(exactly = 0) { }
        verify(exactly = 1) { }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}