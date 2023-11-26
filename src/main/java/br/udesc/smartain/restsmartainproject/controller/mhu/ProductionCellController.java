package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnitRequest;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnitService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent.ProductionCell;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent.ProductionCellRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent.ProductionCellService;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.Sector;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.SectorRequest;
import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.SectorService;
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
@RequestMapping("/api/mhu/productionCell")
public class ProductionCellController {
    
    @Autowired
    private ProductionCellService productionCellService;
    
    @Autowired
    private SectorService sectorService;

    @Autowired
    private ManufacturingUnitService manufacturingUnitService;

    @GetMapping
    public ResponseEntity<List<ProductionCell>> findAll() {
        List<ProductionCell> productionCells = productionCellService.findAll();

        if(productionCells.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(productionCells);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductionCell> findById(@PathVariable Integer id) {
        Optional<ProductionCell> productionCell = productionCellService.findById(id);

        if(productionCell.isEmpty()) {
            throw new NotFoundException("ProductionCell id not found - " + id);
        }

        return ResponseEntity.ok(productionCell.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<ProductionCell>> findAllByStatus(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<ProductionCell> productionCells = productionCellService.findAllByStatus(status);
        if(productionCells.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productionCells);
    }

    @PostMapping
    public ResponseEntity<ProductionCell> createProductionCell(@Valid @RequestBody ProductionCellRequest request) {
        Sector sector = sectorService.findById(request.getSectorId()).orElse(null);
        ManufacturingUnit unit = manufacturingUnitService.findById(sector.getUnit().getId()).orElse(null);

        ProductionCell newProductionCell = new ProductionCell();

        newProductionCell.setSector(sector);
        newProductionCell.setUnit(unit);
        newProductionCell.setName(request.getName());
        newProductionCell.setTag(request.getTag());
        newProductionCell.setDescription(request.getDescription());
        newProductionCell.setStatus(RegisterState.valueOf(request.getStatus().getValue()));
        newProductionCell.setCreatedDate(LocalDateTime.now());

        ProductionCell savedProductionCell = productionCellService.save(newProductionCell);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProductionCell.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductionCell> updateProductionCell(@Valid @RequestBody ProductionCellRequest request, @PathVariable Integer id) {
        Optional<ProductionCell> productionCellToUpdate = productionCellService.findById(id);
        Sector sector = sectorService.findById(request.getSectorId()).orElse(null);
        ManufacturingUnit unit = manufacturingUnitService.findById(sector.getUnit().getId()).orElse(null);

        if(productionCellToUpdate.isEmpty()) {
            throw new NotFoundException("ProductionCell id not found - " + id);
        }

        productionCellToUpdate = productionCellToUpdate.map((productionCellUpdated) -> {
            productionCellUpdated.setDescription(request.getDescription());
            productionCellUpdated.setName(request.getName());
            productionCellUpdated.setTag(request.getTag());
            productionCellUpdated.setSector(sector);
            productionCellUpdated.setUnit(unit);
            productionCellUpdated.setStatus(RegisterState.valueOf(request.getStatus().getValue()));
            productionCellUpdated.setCreatedDate(productionCellUpdated.getCreatedDate());
            return productionCellUpdated;
        });

        return ResponseEntity.ok(productionCellService.save(productionCellToUpdate.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<ProductionCell> productionCell = productionCellService.findById(id);

        if(productionCell.isEmpty()) {
            throw new NotFoundException("ProductionCell id not found - " + id);
        }
        return ResponseEntity.ok(productionCellService.inactiveProductionCell(productionCell.get()));
    }
    
}
