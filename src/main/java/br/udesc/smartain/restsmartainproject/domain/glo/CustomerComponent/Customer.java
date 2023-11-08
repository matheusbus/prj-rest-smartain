package br.udesc.smartain.restsmartainproject.domain.glo.CustomerComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "glo", name = "tbcliente")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clicodigo")
    private Integer id;

    @Column(name = "clirazaosocial", length = 250)
    @Size(min = 5, max = 250, message = "A razão social deve possuir entre 5 e 250 caracteres.")
    private String corporateReason;

    @Column(name = "clicnpjprincipal", nullable = false, unique = true)
    @Size(min = 14, max = 14, message = "O CNPJ deve conter 14 caracteres.")
    private String mainCnpj;

    @Column(name = "clinomefantasia", length = 250)
    @Size(min = 5, max = 250, message = "O nome fantasia deve possuir entre 5 e 250 caracteres.")
    private String fantasyName;

    @OneToMany(mappedBy = "customer")
    private List<ManufacturingUnit> manufacturingUnits;

    public Customer() {

    }

    public Customer(Integer id, String corporateReason, String mainCnpj, String fantasyName) {
        this.id = id;
        this.corporateReason = corporateReason;
        this.mainCnpj = mainCnpj;
        this.fantasyName = fantasyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorporateReason() {
        return corporateReason;
    }

    public void setCorporateReason(String corporateReason) {
        this.corporateReason = corporateReason;
    }

    public String getMainCnpj() {
        return mainCnpj;
    }

    public void setMainCnpj(String mainCnpj) {
        this.mainCnpj = mainCnpj;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
