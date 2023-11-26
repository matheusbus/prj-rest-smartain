package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceSolicitationRepository extends JpaRepository<ServiceSolicitation, Integer> {

    @Query(
            value = "SELECT * FROM mpp.tbservicosolicitacao WHERE tbservicosolicitacao.stsstatus = ?1",
            nativeQuery = true
    )
    public List<ServiceSolicitation> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mpp.tbservicosolicitacao SET tbservicosolicitacao.stsstatus = 2 WHERE tbservicosolicitacao.stscodigo = ?1",
            nativeQuery = true
    )
    public void approveServiceSolicitationById(Integer id);

    @Modifying
    @Query(
            value = "UPDATE mpp.tbservicosolicitacao SET tbservicosolicitacao.stsstatus = 3 WHERE tbservicosolicitacao.stscodigo = ?1",
            nativeQuery = true
    )
    public void denyServiceSolicitationById(Integer id);

    @Modifying
    @Query(
            value = "UPDATE mpp.tbservicosolicitacao SET tbservicosolicitacao.stsstatus = 4 WHERE tbservicosolicitacao.stscodigo = ?1",
            nativeQuery = true
    )
    public void cancelServiceSolicitationById(Integer id);
}
