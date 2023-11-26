package br.udesc.smartain.restsmartainproject.controller;

import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.UserGroup;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.UserGroupService;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.UserRequest;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.UserService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import br.udesc.smartain.restsmartainproject.controller.exception.NotFoundException;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

     @Autowired
    private UserGroupService userGroupService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAllUsers().orElse(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        Optional<User> user = userService.findById(id);

        if (user.isEmpty()) {
            throw new NotFoundException("User id not found - " + id);
        }

        return ResponseEntity.ok(user.get());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest request) {     
        UserGroup userGroup = userGroupService.findById(request.getGroupId()).orElse(null);
         
        User newUser = new User();
        newUser.setLogin(request.getLogin());
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        newUser.setUserGroup(userGroup);
        newUser.setStatus(RegisterState.valueOf(request.getStatus().getValue()));
        newUser.setCreatedDate(LocalDateTime.now());

        User savedUser = userService.save(newUser);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateMachine(@Valid @RequestBody UserRequest request, @PathVariable Integer id) {
        UserGroup userGroup = userGroupService.findById(request.getGroupId()).orElse(null);
        Optional<User> userToUpdate = userService.findById(id);

        LocalDateTime createdDate = userToUpdate.get().getCreatedDate();


        if(userToUpdate.isEmpty()) {
            throw new NotFoundException("User id not found - " + id);
        }

        userToUpdate = userToUpdate.map((userUpdate) -> {
            userUpdate.setUserGroup(userGroup);
            userUpdate.setEmail(request.getEmail());
            userUpdate.setName(request.getName());
            userUpdate.setStatus(request.getStatus());
            userUpdate.setLogin(request.getLogin());
            userUpdate.setPassword(request.getPassword());
            userUpdate.setCreatedDate(createdDate);
            userUpdate.setUpdatedDate(LocalDateTime.now());
            return userUpdate;
        });

        return ResponseEntity.ok(userService.save(userToUpdate.get()));
    }
}
