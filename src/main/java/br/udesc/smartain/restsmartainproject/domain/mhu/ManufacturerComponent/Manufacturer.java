package br.udesc.smartain.restsmartainproject.domain.mhu.ManufacturerComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.MachineModelComponent.MachineModel;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Comment;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "mhu", name = "tbfabricante")
@Comment("Tabela de cadastro dos fabricantes de máquinas/equipamentos/componentes")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fabcodigo")
    @Comment("Código sequencial do fabricante")
    private Integer id;

    @Column(name = "fabrazaosocial", length = 150)
    @Size(min = 5, max = 150, message = "A razão social deve possuir entre 5 e 150 caracteres.")
    @Comment("Razão social do fabricante")
    private String socialReason;

    @Column(name = "fabcnpj", nullable = false, unique = true)
    @Size(min = 14, max = 14, message = "O CNPJ deve conter 14 caracteres.")
    @Comment("CNPJ do fabricante")
    private String cnpj;

    @Column(name = "fabtelefone", length = 20)
    @Size(max = 20, message = "O telefone deve possuir no máximo 20 caracteres.")
    @Comment("Telefone de contato do fabricante")
    private String phone;

    @Column(name = "fabemail", length = 100)
    @Size(max = 100, message = "O e-mail deve possuir no máximo 100 caracteres.")
    @Comment("E-mail de contato do fabricante")
    private String email;

    @Column(name = "fabstatus")
    @Comment("Status do Fabricante (1-Ativo 2-Inativo)")
    @Range(min = 1, max = 2)
    private Short status;

    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private List<MachineModel> machineModels = new ArrayList<>();

    public Manufacturer() {

    }

    public Manufacturer(Integer id, String socialReason, String cnpj, String phone, String email, RegisterState status) {
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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public List<MachineModel> getMachineModels() {
        return machineModels;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", socialReason='" + socialReason + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
