package br.udesc.smartain.restsmartainproject.controller.mhu;

import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import br.udesc.smartain.restsmartainproject.domain.mhu.RoleComponent.RoleService;
import br.udesc.smartain.restsmartainproject.domain.mhu.RoleComponent.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mhu/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        List<Role> Roles = roleService.findAll();

        if(Roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(Roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Integer id) {
        Optional<Role> Role = roleService.findById(id);

        if(Role.isEmpty()) {
            throw new NotFoundException("Role id not found - " + id);
        }

        return ResponseEntity.ok(Role.get());
    }

    @GetMapping(params = "status")
    public ResponseEntity<List<Role>> findAllByStatys(@RequestParam(name = "status") Short status) {
        if(status != 1 && status != 2) {
            throw new NotFoundException("Status code not found - " + status);
        }
        List<Role> Roles = roleService.findAllByStatus(status);
        if(Roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Roles);
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role Role) {
        Role newRole = roleService.save(Role);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newRole.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@Valid @RequestBody Role newRole, @PathVariable Integer id) {
        Optional<Role> roleToUpdate = roleService.findById(id);

        if(roleToUpdate.isEmpty()) {
            throw new NotFoundException("Role id not found - " + id);
        }

        roleToUpdate = roleToUpdate.map((roleUpdated) -> {
            roleUpdated.setName(newRole.getName());
            roleUpdated.setDescription(newRole.getDescription());
            roleUpdated.setStatus(newRole.getStatus());
            return roleUpdated;
        });

        return ResponseEntity.ok(roleToUpdate.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        Optional<Role> Role = roleService.findById(id);

        if(Role.isEmpty()) {
            throw new NotFoundException("Role id not found - " + id);
        }
        return ResponseEntity.ok(roleService.inactiveRole(Role.get()));
    }

}
