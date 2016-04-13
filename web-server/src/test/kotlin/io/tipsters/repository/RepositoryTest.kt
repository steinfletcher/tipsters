package io.tipsters.repository

import io.tipsters.Application
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringJUnit4ClassRunner::class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = arrayOf(Application::class))
abstract class RepositoryTest
