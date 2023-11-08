package br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CountryComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent.FederativeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public List<Country> findAllCountries() {
        return countryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Optional<List<Country>> findByName(String name) {
        return countryRepository.findByName(name);
    }

    public Optional<Country> findById(Integer id) {
        return countryRepository.findById(id);
    }

    public List<FederativeUnit> listFederativeUnitByCountry(Country country) {
        return country.getUnits().stream().toList();
    }

    @Transactional
    public void save(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    public void saveAll(List<Country> countries) {
        countryRepository.saveAll(countries);
    }

}
