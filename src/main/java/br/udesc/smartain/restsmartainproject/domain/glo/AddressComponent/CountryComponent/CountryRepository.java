package br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CountryComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    public List<Country> findCountryByName(String name);

    @Query(
            value = "SELECT * FROM glo.tbpais WHERE painome ILIKE %?1% ORDER BY tbpais.painome",
            nativeQuery = true)
    public Optional<List<Country>> findByName(String name);

    @Query(
            value = "SELECT * FROM glo.tbpais ORDER BY tbpais.painome",
            nativeQuery = true)
    public Optional<List<Country>> findAllCountries();
}
