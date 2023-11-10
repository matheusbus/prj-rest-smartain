package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.ShiftComponent.Shift;
import br.udesc.smartain.restsmartainproject.domain.mhu.ShiftComponent.ShiftRepository;
import br.udesc.smartain.restsmartainproject.domain.mhu.ShiftComponent.ShiftService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/shift")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @GetMapping
    public ResponseEntity<List<Shift>> findAll() {
        List<Shift> shifts = shiftService.findAll();

        if(shifts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(shifts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shift> findById(@PathVariable Integer id) {
        Optional<Shift> shift = shiftService.findById(id);

        if(shift.isEmpty()) {
            throw new NotFoundException("Shift id not found - " + id);
        }

        return ResponseEntity.ok(shift.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Shift>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 || status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<Shift> shifts = shiftService.findAllByStatus(status);
        if(shifts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(shifts);
    }

    @PostMapping
    public ResponseEntity<Shift> createShift(@Valid @RequestBody Shift shift) {
        Shift newShift = shiftService.save(shift);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newShift.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shift> updateShift(@Valid @RequestBody Shift newShift, @PathVariable Integer id) {
        Optional<Shift> shiftToUpdate = shiftService.findById(id);

        if(shiftToUpdate.isEmpty()) {
            throw new NotFoundException("Shift id not found - " + id);
        }

        shiftToUpdate = shiftToUpdate.map((shiftUpdated) -> {
            shiftUpdated.setDescritpion(newShift.getDescritpion());
            shiftUpdated.setInitHour(newShift.getInitHour());
            shiftUpdated.setEndHour(newShift.getEndHour());
            shiftUpdated.setStatus(newShift.getStatus());
            return shiftUpdated;
        });

        return ResponseEntity.ok(shiftToUpdate.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<Shift> shift = shiftService.findById(id);

        if(shift.isEmpty()) {
            throw new NotFoundException("Shift id not found - " + id);
        }
        return ResponseEntity.ok(shiftService.inactiveShift(shift.get()));
    }

}
