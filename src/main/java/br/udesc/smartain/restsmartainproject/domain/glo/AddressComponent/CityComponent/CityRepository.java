package br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {

    @Query(
            value = "SELECT * FROM glo.tbcidade",
            nativeQuery = true
    )
    public Optional<List<City>> findAllCities();

    @Query(
            value = "SELECT * FROM glo.tbcidade WHERE tbcidade.paicodigo = ?1 AND tbcidade.unfcodigo = ?2 AND tbcidade.cidcodigo = ?3",
            nativeQuery = true
    )
    public Optional<City> findByCountryIdAndFederativeUnitIdAndCityId(Integer countryId, Integer fedUnitId, Integer cityId);

    @Query(
            value = "SELECT * FROM glo.tbcidade WHERE cidnome ILIKE %?1%",
            nativeQuery = true
    )
    public Optional<List<City>> findByName(String name);

    @Query(
            value = "SELECT * FROM glo.tbcidade WHERE tbcidade.paicodigo = ?1 AND tbcidade.unfcodigo = ?2",
            nativeQuery = true
    )
    public Optional<List<City>> findByCountryIdAndFederativeUnitId(Integer countryId, Integer federativeUnitId);

}
