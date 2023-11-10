package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.UnitTypeComponent.UnitType;
import br.udesc.smartain.restsmartainproject.domain.mhu.UnitTypeComponent.UnitTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/unitType")
public class UnitTypeController {

    @Autowired
    private UnitTypeService unitTypeService;

    @GetMapping
    public ResponseEntity<List<UnitType>> findAll() {
        List<UnitType> types = unitTypeService.findAll();
        return ResponseEntity.ok(types);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitType> findById(@PathVariable Integer id) {
        Optional<UnitType> type = unitTypeService.findById(id);

        if(type.isEmpty()) {
            throw new NotFoundException("UnitType id not found - " + id);
        }

        return ResponseEntity.ok(type.get());
    }


}
