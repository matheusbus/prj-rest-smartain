package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.Sector;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.SectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/sector")
public class SectorController {

    @Autowired
    private SectorService sectorService;

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
    public ResponseEntity<Sector> createSector(@Valid @RequestBody Sector sector) {
        Sector newSector = sectorService.save(sector);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newSector.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sector> updateSector(@Valid @RequestBody Sector newSector, @PathVariable Integer id) {
        Optional<Sector> sectorToUpdate = sectorService.findById(id);

        if(sectorToUpdate.isEmpty()) {
            throw new NotFoundException("Sector id not found - " + id);
        }

        sectorToUpdate = sectorToUpdate.map((sectorUpdated) -> {
            sectorUpdated.setName(newSector.getName());
            sectorUpdated.setDescription(newSector.getDescription());
            sectorUpdated.setTag(newSector.getTag());
            sectorUpdated.setStatus(newSector.getStatus());
            sectorUpdated.setCreatedDate(newSector.getCreatedDate());
            sectorUpdated.setUnit(newSector.getUnit());
            return sectorUpdated;
        });

        return ResponseEntity.ok(sectorToUpdate.get());
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
