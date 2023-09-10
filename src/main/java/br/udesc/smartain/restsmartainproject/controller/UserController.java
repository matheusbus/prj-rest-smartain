package br.udesc.smartain.restsmartainproject.controller;

import br.udesc.smartain.restsmartainproject.domain.model.glo.User;
import br.udesc.smartain.restsmartainproject.domain.service.glo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public List<User> findAll(){
        return userService.findAllUsers().orElse(null);
    }
}
