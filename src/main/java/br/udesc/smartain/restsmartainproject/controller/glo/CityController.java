package br.udesc.smartain.restsmartainproject.controller.glo;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent.City;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/glo/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> findAll() {
        List<City> cities = cityService.findAll().get();

        if(cities.isEmpty()) {
            throw new NotFoundException("No one city registered yet.");
        }

        return ResponseEntity.ok(cities);
    }

    @GetMapping(params = {"countryId", "federativeUnitId", "cityId"})
    public ResponseEntity<City> findByCompositeId(@RequestParam("countryId") Integer countryId,
                                                  @RequestParam("federativeUnitId") Integer federativeUnitId,
                                                  @RequestParam("cityId") Integer cityId) {
        Optional<City> city = cityService.findByCountryIdAndFederativeUnitIdAndCityId(countryId, federativeUnitId, cityId);

        if(city.isEmpty()) {
            throw new NotFoundException("City not found with composite id - Country: " + countryId + ", FederativeUnit: " + federativeUnitId + ", City: " + cityId);
        }

        return ResponseEntity.ok(city.get());
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<City>> findByName(String name) {
        List<City> cities = cityService.findByName(name).get();

        if(cities.isEmpty()) {
            throw new NotFoundException("No one city was found with the name '" + name + "'.");
        }

        return ResponseEntity.ok(cities);
    }

}
