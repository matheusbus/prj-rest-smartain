package br.udesc.smartain.restsmartainproject.domain.glo.UserComponent;

import java.time.LocalDateTime;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;

public class UserRequest {
    
    private Long groupId;
    private String name;    
    private String login;
    private String email;
    private RegisterState status;
    private LocalDateTime createdData;
    private String password;

    
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
