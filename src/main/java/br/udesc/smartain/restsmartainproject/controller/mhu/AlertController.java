package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.UserService;
import br.udesc.smartain.restsmartainproject.domain.mhu.AlertComponent.*;
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
@RequestMapping("/api/mhu/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Alert>> findAll() {
        List<Alert> alerts = alertService.findAll();
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alert> findById(@PathVariable Integer id) {
        Optional<Alert> alert = alertService.findById(id);

        if(alert.isEmpty()) {
            throw new NotFoundException("Alert id not found - " + id);
        }

        return ResponseEntity.ok(alert.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Alert>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<Alert> alerts = alertService.findAllByStatus(status);
        if(alerts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alerts);
    }

    @GetMapping(params = "type")
    public ResponseEntity<List<Alert>> findAllByType(@RequestParam(name = "type") Short type) {
        if(type != 1 && type != 2) {
            throw new NotFoundException("Type code not found - " + type);
        }
        List<Alert> alerts = alertService.findAllByType(type);
        if(alerts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alerts);
    }

    @PostMapping
    public ResponseEntity<Alert> createAlert(@Valid @RequestBody AlertRequest request) {
        Alert newAlert = new Alert();
        User user = userService.findById(request.getUserId()).get();

        newAlert.setCreatedDate(LocalDateTime.now());
        newAlert.setTitle(request.getTitle());
        newAlert.setDescription(request.getDescription());
        newAlert.setExpirationDate(request.getExpirationDate());
        newAlert.setStatus(AlertStatus.valueOf(request.getStatus()));
        newAlert.setType(AlertType.valueOf(request.getType()));
        newAlert.setCreatedUser(user);

        Alert savedAlert = alertService.save(newAlert);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAlert.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alert> updateAlert(@Valid @RequestBody AlertRequest request, @PathVariable Integer id) {
        Optional<Alert> alertToUpdate = alertService.findById(id);
        Optional<User> user = userService.findById((request.getUserId() != null) ? request.getUserId() : 0);
        if(alertToUpdate.isEmpty()) {
            throw new NotFoundException("Alert or User id not found - " + id);
        }

        alertToUpdate = alertToUpdate.map((alertUpdated) -> {
            alertUpdated.setTitle(request.getTitle());
            alertUpdated.setDescription(request.getDescription());
            alertUpdated.setType(AlertType.valueOf(request.getType()));
            alertUpdated.setStatus(AlertStatus.valueOf(request.getStatus()));
            alertUpdated.setCreatedDate(alertUpdated.getCreatedDate());
            alertUpdated.setExpirationDate(request.getExpirationDate());
            alertUpdated.setCreatedUser(user.orElse(null));
            return alertUpdated;
        });

        return ResponseEntity.ok(alertService.save(alertToUpdate.get()));
    }

}
