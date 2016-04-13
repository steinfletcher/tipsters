package io.tipsters.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableJpaRepositories(basePackages = arrayOf("io.tipsters.repository"))
@EnableTransactionManagement
open class PersistenceConfig {}
