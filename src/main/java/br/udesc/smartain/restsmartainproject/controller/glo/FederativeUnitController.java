package br.udesc.smartain.restsmartainproject.controller.glo;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent.City;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CountryComponent.Country;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CountryComponent.CountryService;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent.FederativeUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent.FederativeUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/glo/federativeUnit")
public class FederativeUnitController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private FederativeUnitService federativeUnitService;

    @GetMapping
    public ResponseEntity<List<FederativeUnit>> findAll() {
        return ResponseEntity.ok(federativeUnitService.findAll());
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<FederativeUnit>> findByName(@RequestParam(name = "name") String name) {
        Optional<List<FederativeUnit>> federativeUnits = federativeUnitService.findByName(name);

        if(federativeUnits.get().isEmpty()) {
            throw new NotFoundException("Federative Unit not found for name: " + name);
        }

        return ResponseEntity.ok(federativeUnits.get().stream().toList());
    }

    @GetMapping(params = "countryId")
    public ResponseEntity<List<FederativeUnit>> findByCountryId(@RequestParam(name = "countryId") Integer id) {
        Optional<Country> country = countryService.findById(id);

        if(country.isEmpty()) {
            throw new NotFoundException("Country id not found - " + id);
        }

        return ResponseEntity.ok(countryService.listFederativeUnitByCountry(country.get()));
    }

    @GetMapping(params = {"countryId", "federativeUnitId"})
    public ResponseEntity<FederativeUnit> findByCountryIdAndFederativeUnitId(@RequestParam(name = "countryId") Integer countryId, @RequestParam("federativeUnitId") Integer unitId) {
        Optional<Country> country = countryService.findById(countryId);

        if(country.isEmpty()) {
            throw new NotFoundException("Country id not found - " + countryId);
        }

        Optional<FederativeUnit> federativeUnit = federativeUnitService.findByCountryIdAndFederativeUnitId(countryId, unitId);

        if(federativeUnit.isEmpty()) {
            throw new NotFoundException("Federative Unit id not found - " + unitId + " for the country " + country.get().getName());
        }

        return ResponseEntity.ok(federativeUnit.get());
    }

    @GetMapping(path = "/cities", params = {"countryId", "federativeUnitId"})
    public ResponseEntity<List<City>> getCitiesByCountryIdAndFederativeUnitId(@RequestParam(name = "countryId") Integer countryId, @RequestParam("federativeUnitId") Integer unitId) {
        Optional<FederativeUnit> federativeUnit = federativeUnitService.findByCountryIdAndFederativeUnitId(countryId, unitId);

        if(federativeUnit.isEmpty()) {
            throw new NotFoundException("Federative Unit id not found - Unit Id: " + unitId + " and Country Id: " + countryId);
        }

        return ResponseEntity.ok(federativeUnit.get().getCities());
    }

}
