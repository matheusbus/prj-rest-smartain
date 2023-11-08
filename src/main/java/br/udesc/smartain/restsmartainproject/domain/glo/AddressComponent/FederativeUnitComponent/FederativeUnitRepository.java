package br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CountryComponent.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FederativeUnitRepository extends JpaRepository<FederativeUnit, FederativeUnitId> {

    public List<FederativeUnit> findByIdCountry(Country country);

    @Query(
            value = "SELECT * FROM glo.tbunifederativa WHERE tbunifederativa.unfnome ILIKE %?1%",
            nativeQuery = true
    )
    public Optional<List<FederativeUnit>> findByName(String name);


    @Query(
            value = "SELECT * FROM glo.tbunifederativa WHERE tbunifederativa.paicodigo = ?1 and tbunifederativa.unfcodigo = ?2",
            nativeQuery = true
    )
    public Optional<FederativeUnit> findByCountryIdAndFederativeUnitId(Integer countryId, Integer federativeUnitId);

    @Query(
            value = "SELECT * FROM glo.tbunifederativa",
            nativeQuery = true
    )
    public Optional<List<FederativeUnit>> findAllFederativeUnits();



}
