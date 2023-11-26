package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent.ProductionCell;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProductionCellComponent.ProductionCellService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/productionCell")
public class ProductionCellController {
    
    @Autowired
    private ProductionCellService productionCellService;

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
    public ResponseEntity<ProductionCell> createProductionCell(@Valid @RequestBody ProductionCell productionCell) {
        ProductionCell newProductionCell = productionCellService.save(productionCell);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProductionCell.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductionCell> updateProductionCell(@Valid @RequestBody ProductionCell newProductionCell, @PathVariable Integer id) {
        Optional<ProductionCell> productionCellToUpdate = productionCellService.findById(id);

        if(productionCellToUpdate.isEmpty()) {
            throw new NotFoundException("ProductionCell id not found - " + id);
        }

        productionCellToUpdate = productionCellToUpdate.map((productionCellUpdated) -> {
            productionCellUpdated.setDescription(newProductionCell.getDescription());
            productionCellUpdated.setName(newProductionCell.getName());
            productionCellUpdated.setTag(newProductionCell.getTag());
            productionCellUpdated.setSector(newProductionCell.getSector());
            productionCellUpdated.setUnit(newProductionCell.getUnit());
            productionCellUpdated.setStatus(newProductionCell.getStatus());
            productionCellUpdated.setCreatedDate(newProductionCell.getCreatedDate());
            return productionCellUpdated;
        });

        return ResponseEntity.ok(productionCellToUpdate.get());
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
