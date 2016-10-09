package io.tipsters.uiapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import io.tipsters.uiapi.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {
}
