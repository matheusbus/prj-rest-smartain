package br.udesc.smartain.restsmartainproject.domain.mhu.SupplierComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Range;

import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbfornecedor")
@Comment("Tabela de cadastros dos fornecedores")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forcodigo")
    @Comment("Código sequencial do fornecedor")
    private Integer id;

    @Column(name = "forrazaosocial", length = 150)
    @Size(min = 5, max = 150, message = "A razão social deve possuir entre 5 e 150 caracteres.")
    @Comment("Razão social do fornecedor")
    private String socialReason;

    @Column(name = "forcnpj", nullable = false, unique = true)
    @Size(min = 14, max = 14, message = "O CNPJ deve conter 14 caracteres.")
    @Comment("CNPJ do fornecedor")
    private String cnpj;

    @Column(name = "fortelefone", length = 20)
    @Size(max = 20, message = "O telefone deve possuir no máximo 20 caracteres.")
    @Comment("Telefone de contato do fornecedor")
    private String phone;

    @Column(name = "foremail", length = 100)
    @Size(max = 100, message = "O e-mail deve possuir no máximo 100 caracteres.")
    @Comment("E-mail de contato do fornecedor")
    private String email;

    @Column(name = "forstatus")
    @Comment("Status do Fornecedor (1-Ativo 2-Inativo)")
    @Range(min = 1, max = 2)
    private Short status;

    public Supplier() {

    }

    public Supplier(Integer id, String socialReason, String cnpj, String phone, String email, RegisterState status) {
        this.id = id;
        this.socialReason = socialReason;
        this.cnpj = cnpj;
        this.phone = phone;
        this.email = email;
        this.status = status.getValue();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return RegisterState.valueOf(status);
    }

    public void setStatus(RegisterState status) {
        this.status = status.getValue();
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", socialReason='" + socialReason + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(id, supplier.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
