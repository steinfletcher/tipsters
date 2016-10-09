package io.tipsters.uiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import io.tipsters.uiapi.domain.Competition;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

  @Query(nativeQuery = true, value = "select c.name from competitions c where cast(c.id as text) in :ids")
  Set<String> findCompetitionNamesByIdIn(@Param("ids") List<String> ids);
}
