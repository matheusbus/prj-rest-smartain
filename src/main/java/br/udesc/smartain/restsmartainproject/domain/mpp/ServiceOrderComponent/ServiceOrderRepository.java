package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent;

import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Integer> {

    @Query(
            value = "SELECT * FROM mpp.tbservicoordem WHERE tbservicoordem.svostatus = ?1",
            nativeQuery = true
    )
    public List<ServiceOrder> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mpp.tbservicoordem SET tbservicoordem.svostatus = 3 WHERE tbservicoordem.svocodigo = ?1",
            nativeQuery = true
    )
    public void initServiceOrderById(Integer id);

    @Modifying
    @Query(
            value = "UPDATE mpp.tbservicoordem SET tbservicoordem.svostatus = 2 WHERE tbservicoordem.svocodigo = ?1",
            nativeQuery = true
    )
    public void scheduleServiceOrderById(Integer id);

    @Modifying
    @Query(
            value = "UPDATE mpp.tbservicoordem SET tbservicoordem.svostatus = 4 WHERE tbservicoordem.svocodigo = ?1",
            nativeQuery = true
    )
    public void suspendServiceOrderById(Integer id);

    @Modifying
    @Query(
            value = "UPDATE mpp.tbservicoordem SET tbservicoordem.svostatus = 4 WHERE tbservicoordem.svocodigo = ?1",
            nativeQuery = true
    )
    public void completeServiceOrderById(Integer id);

}
