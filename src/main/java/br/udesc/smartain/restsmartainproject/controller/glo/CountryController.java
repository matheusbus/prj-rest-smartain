package br.udesc.smartain.restsmartainproject.controller.glo;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CountryComponent.Country;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CountryComponent.CountryService;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.FederativeUnitComponent.FederativeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/glo/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> findAll() {
        return ResponseEntity.ok(countryService.findAllCountries());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Country> findById(@PathVariable Integer id) {
        Optional<Country> country = countryService.findById(id);

        if(country.isEmpty()) {
            throw new NotFoundException("Country id not found - " + id);
        }

        return ResponseEntity.ok(country.get());
    }

    @GetMapping(path = "/{id}/units")
    public ResponseEntity<List<FederativeUnit>> listUnitsByCountry(@PathVariable Integer id) {
        Optional<Country> country = countryService.findById(id);

        if(country.isEmpty()) {
            throw new NotFoundException("Country id not found - " + id);
        }

        if(country.get().getUnits().isEmpty()) {
            throw new NotFoundException("The country " + country.get().getName() + " does not have federative units registered in the system.");
        }

        return ResponseEntity.ok(countryService.listFederativeUnitByCountry(country.get()));
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<Country>> findByName(@RequestParam(name = "name") String name) {
        Optional<List<Country>> countries = countryService.findByName(name);

        if(countries.get().isEmpty()) {
            throw new NotFoundException("Country name not found for name: " + name);
        }

        return ResponseEntity.ok(countries.get());
    }

}
