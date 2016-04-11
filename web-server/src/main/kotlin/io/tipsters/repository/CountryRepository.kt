package io.tipsters.repository

import io.tipsters.domain.Country
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CountryRepository : JpaRepository<Country, UUID>
