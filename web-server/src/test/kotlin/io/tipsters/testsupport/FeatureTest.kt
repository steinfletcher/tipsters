package io.tipsters.testsupport

import io.tipsters.Application
import io.tipsters.config.PersistenceConfig
import io.tipsters.testsupport.stub.WilliamHillOddsFeedStub
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = arrayOf(
        Application::class, PersistenceConfig::class
))
abstract class FeatureTest {
    @Autowired
    protected lateinit var context: WebApplicationContext
    protected lateinit var mockMvc: MockMvc
    val oddsFeedStub = WilliamHillOddsFeedStub(19001)

    @Before
    fun setUp() {
        oddsFeedStub.start()
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @After
    fun teardown() {
        oddsFeedStub.stop()
    }
}