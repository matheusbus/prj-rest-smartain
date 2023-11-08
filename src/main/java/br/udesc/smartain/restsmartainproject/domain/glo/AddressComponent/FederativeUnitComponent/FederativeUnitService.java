package br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent.City;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CountryComponent.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FederativeUnitService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private FederativeUnitRepository federativeUnitRepository;

    public List<FederativeUnit> findAll() {
        return federativeUnitRepository.findAll();
    }

    public List<FederativeUnit> findByCountryId(Integer id) {
        return federativeUnitRepository.findByIdCountry(countryRepository.findById(id).get());
    }

    public Optional<List<FederativeUnit>> findByName(String name) {
        return federativeUnitRepository.findByName(name);
    }

    public Optional<FederativeUnit> findByCountryIdAndFederativeUnitId(Integer countryId, Integer unitId) {
        return federativeUnitRepository.findByCountryIdAndFederativeUnitId(countryId, unitId);
    }

    public List<City> getCitiesByFederativeUnit(FederativeUnit federativeUnit) {
        return federativeUnit.getCities();
    }

    @Transactional
    public void saveAll(List<FederativeUnit> units) {
        federativeUnitRepository.saveAll(units);
    }

}
