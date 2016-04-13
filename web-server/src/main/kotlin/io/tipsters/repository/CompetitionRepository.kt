package io.tipsters.repository

import io.tipsters.domain.Competition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CompetitionRepository : JpaRepository<Competition, UUID> {
    fun findByIdIn(ids: List<UUID>): List<Competition>
}
