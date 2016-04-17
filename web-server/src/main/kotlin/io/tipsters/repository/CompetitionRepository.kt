package io.tipsters.repository

import io.tipsters.domain.Competition
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CompetitionRepository : JpaRepository<Competition, UUID> {
    @Query(nativeQuery = true, value = "select c.name from competitions c where cast(c.id as text) in :ids")
    fun findCompetitionNamesByIdIn(@Param("ids") ids: List<String>): Set<String>
}
