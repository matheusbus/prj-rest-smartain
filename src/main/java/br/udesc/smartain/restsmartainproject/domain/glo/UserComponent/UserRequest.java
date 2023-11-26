package br.udesc.smartain.restsmartainproject.domain.glo.UserComponent;

import java.time.LocalDateTime;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;

public class UserRequest {
    
    private String name;    
    private String login;
    private String email;
    private RegisterState status;
    private LocalDateTime createdData;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public RegisterState getStatus() {
        return status;
    }
    public void setStatus(RegisterState status) {
        this.status = status;
    }
    public LocalDateTime getCreatedData() {
        return createdData;
    }
    public void setCreatedData(LocalDateTime createdData) {
        this.createdData = createdData;
    }

    
}