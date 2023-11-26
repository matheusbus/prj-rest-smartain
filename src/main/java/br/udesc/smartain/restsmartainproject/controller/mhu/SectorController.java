package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnitService;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.Sector;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.SectorRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.SectorService;
import br.udesc.smartain.restsmartainproject.domain.mhu.UnitTypeComponent.UnitType;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/sector")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @Autowired
    private ManufacturingUnitService manufacturingUnitService;

    @GetMapping
    public ResponseEntity<List<Sector>> findAll() {
        List<Sector> sectors = sectorService.findAll();

        if(sectors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(sectors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sector> findById(@PathVariable Integer id) {
        Optional<Sector> sector = sectorService.findById(id);

        if(sector.isEmpty()) {
            throw new NotFoundException("Sector id not found - " + id);
        }

        return ResponseEntity.ok(sector.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Sector>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<Sector> sectors = sectorService.findAllByStatus(status);
        if(sectors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sectors);
    }

    @PostMapping
    public ResponseEntity<Sector> createSector(@Valid @RequestBody SectorRequest request) {
        ManufacturingUnit unit = manufacturingUnitService.findById(request.getUnitId()).orElse(null);

        Sector newSector = new Sector();
        newSector.setUnit(unit);
        newSector.setName(request.getName());
        newSector.setTag(request.getTag());
        newSector.setDescription(request.getDescription());
        newSector.setStatus(RegisterState.valueOf(request.getStatus().getValue()));
        newSector.setCreatedDate(LocalDateTime.now());

        Sector savedSector = sectorService.save(newSector);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedSector.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sector> updateSector(@Valid @RequestBody SectorRequest request, @PathVariable Integer id) {
        Optional<Sector> sectorToUpdate = sectorService.findById(id);
        ManufacturingUnit unit = manufacturingUnitService.findById(request.getUnitId()).orElse(null);


        if(sectorToUpdate.isEmpty()) {
            throw new NotFoundException("Sector id not found - " + id);
        }

        sectorToUpdate = sectorToUpdate.map((sectorUpdated) -> {
            sectorUpdated.setName(request.getName());
            sectorUpdated.setDescription(request.getDescription());
            sectorUpdated.setTag(request.getTag());
            sectorUpdated.setStatus(RegisterState.valueOf(request.getStatus().getValue()));
            sectorUpdated.setCreatedDate(request.getCreatedDate());
            sectorUpdated.setUnit(unit);
            return sectorUpdated;
        });

        return ResponseEntity.ok(sectorService.save(sectorToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<Sector> sector = sectorService.findById(id);

        if(sector.isEmpty()) {
            throw new NotFoundException("Sector id not found - " + id);
        }
        return ResponseEntity.ok(sectorService.inactiveSector(sector.get()));
    }
    
}
