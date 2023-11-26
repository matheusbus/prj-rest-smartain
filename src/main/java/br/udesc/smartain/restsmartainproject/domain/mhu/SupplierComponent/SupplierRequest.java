package br.udesc.smartain.restsmartainproject.domain.mhu.SupplierComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;

public class SupplierRequest {
        private String socialReason;
        private String cnpj;
        private String phone;
        private String email;
        private RegisterState status;

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
