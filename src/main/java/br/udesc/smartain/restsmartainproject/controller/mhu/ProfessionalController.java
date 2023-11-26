package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProfessionalComponent.Professional;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProfessionalComponent.ProfessionalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/professional")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @GetMapping
    public ResponseEntity<List<Professional>> findAll() {
        List<Professional> professionals = professionalService.findAll();

        if(professionals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(professionals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professional> findById(@PathVariable Integer id) {
        Optional<Professional> professional = professionalService.findById(id);

        if(professional.isEmpty()) {
            throw new NotFoundException("Professional id not found - " + id);
        }

        return ResponseEntity.ok(professional.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Professional>> findAllByStatus(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<Professional> professional = professionalService.findAllByStatus(status);
        if(professional.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(professional);
    }

    @PostMapping
    public ResponseEntity<Professional> createProfessional(@Valid @RequestBody Professional professional) {
        Professional newProfessional = professionalService.save(professional);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProfessional.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professional> updateManufacturer(@Valid @RequestBody Professional newProfessional, @PathVariable Integer id) {
        Optional<Professional> professionalToUpdate = professionalService.findById(id);

        if(professionalToUpdate.isEmpty()) {
            throw new NotFoundException("Manufacturer id not found - " + id);
        }

        professionalToUpdate = professionalToUpdate.map((professionalUpdated) -> {
            professionalUpdated.setName(newProfessional.getName());
            professionalUpdated.setBirthDate(newProfessional.getBirthDate());
            professionalUpdated.setCpf(newProfessional.getCpf());
            professionalUpdated.setUser(newProfessional.getUser());
            professionalUpdated.setStatus(newProfessional.getStatus());
            professionalUpdated.setCreatedDate(newProfessional.getCreatedDate());
            return professionalUpdated;
        });

        return ResponseEntity.ok(professionalToUpdate.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<Professional> professional = professionalService.findById(id);

        if(professional.isEmpty()) {
            throw new NotFoundException("Professional id not found - " + id);
        }
        return ResponseEntity.ok(professionalService.inactiveProfesional(professional.get()));
    }


}
