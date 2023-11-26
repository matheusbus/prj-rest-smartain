package br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public Optional<City> findTeste(Integer id) { return cityRepository.findTeste(id); }

    public Optional<List<City>> findAll() {
        return cityRepository.findAllCities();
    }

    public Optional<City> findByCountryIdAndFederativeUnitIdAndCityId(Integer countryId, Integer fedUnitId, Integer cityId) {
        return cityRepository.findByCountryIdAndFederativeUnitIdAndCityId(countryId, fedUnitId, cityId);
    }

    public Optional<List<City>> findByName(String name) {
        return cityRepository.findByName(name);
    }

    public void save(City city) {
        cityRepository.save(city);
    }

    public void saveAll(List<City> cities) {
        cityRepository.saveAll(cities);
    }

}
