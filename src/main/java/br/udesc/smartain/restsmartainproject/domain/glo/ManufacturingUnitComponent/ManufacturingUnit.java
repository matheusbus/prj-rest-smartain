package br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent;

import br.udesc.smartain.restsmartainproject.domain.glo.CustomerComponent.Customer;
import br.udesc.smartain.restsmartainproject.domain.glo.AddressComponent.CityComponent.City;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(schema = "glo", name = "tbunidadefabril")
public class ManufacturingUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ufbcodigo")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "clicodigo")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumns({@JoinColumn(name = "cidcodigo"), @JoinColumn(name = "unfcodigo"), @JoinColumn(name = "paicodigo")})
    private City city;

    @Column(name = "ufbendereco", nullable = false)
    @Size(max = 300, message = "O endereço deve conter no máximo 300 caracteres.")
    private String address;

    @Column(name = "ufbstatus")
    private Short status;

    public ManufacturingUnit() {

    }

    public ManufacturingUnit(Integer id, Customer customer, City city, String address, RegisterState status) {
        this.id = id;
        this.customer = customer;
        this.city = city;
        this.address = address;
        this.status = status.getValue();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RegisterState getStatus() {
        return RegisterState.valueOf(this.status);
    }

    public void setStatus(RegisterState status) {
        this.status = status.getValue();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturingUnit that = (ManufacturingUnit) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
