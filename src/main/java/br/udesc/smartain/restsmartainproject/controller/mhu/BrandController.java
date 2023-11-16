package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent.BrandService;
import br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent.Brand;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> findAll() {
        List<Brand> brands = brandService.findAll();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> findById(@PathVariable Integer id) {
        Optional<Brand> brand = brandService.findById(id);

        if(brand.isEmpty()) {
            throw new NotFoundException("Brand id not found - " + id);
        }

        return ResponseEntity.ok(brand.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Brand>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<Brand> brands = brandService.findAllByStatus(status);
        if(brands.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(brands);
    }

    @PostMapping
    public ResponseEntity<Brand> createBrand(@Valid @RequestBody Brand brand) {
        Brand newBrand = brandService.save(brand);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBrand.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@Valid @RequestBody Brand newBrand, @PathVariable Integer id) {
        Optional<Brand> brandToUpdate = brandService.findById(id);

        if(brandToUpdate.isEmpty()) {
            throw new NotFoundException("Brand id not found - " + id);
        }

        brandToUpdate = brandToUpdate.map((brandUpdated) -> {
            brandUpdated.setName(newBrand.getName());
            brandUpdated.setCreatedDate(newBrand.getCreatedDate());
            brandUpdated.setUser(newBrand.getUser());
            brandUpdated.setStatus(newBrand.getStatus());
            return brandUpdated;
        });

        return ResponseEntity.ok(brandToUpdate.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<Brand> brand = brandService.findById(id);

        if(brand.isEmpty()) {
            throw new NotFoundException("Brand id not found - " + id);
        }
        return ResponseEntity.ok(brandService.inactiveBrand(brand.get()));
    }

}
