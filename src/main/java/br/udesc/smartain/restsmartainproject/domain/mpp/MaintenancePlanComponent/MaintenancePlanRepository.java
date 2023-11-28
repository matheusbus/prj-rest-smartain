package br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaintenancePlanRepository extends JpaRepository<MaintenancePlan, Integer> {

    @Query(
            value = "SELECT * FROM mpp.tbplanomanutencao WHERE tbplanomanutencao.plmstatus = ?1",
            nativeQuery = true
    )
    public List<MaintenancePlan> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mpp.tbplanomanutencao SET tbplanomanutencao.plmstatus = 2 WHERE tbplanomanutencao.plmcodigo = ?1",
            nativeQuery = true
    )
    public void initMaintenancePlanById(Integer id);

    @Modifying
    @Query(
            value = "UPDATE mpp.tbplanomanutencao SET tbplanomanutencao.plmstatus = 3 WHERE tbplanomanutencao.plmcodigo = ?1",
            nativeQuery = true
    )
        public void completeMaintenancePlanById(Integer id);

    @Modifying
    @Query(
            value = "UPDATE mpp.tbplanomanutencao SET tbplanomanutencao.plmstatus = 4 WHERE tbplanomanutencao.plmcodigo = ?1",
            nativeQuery = true
    )
    public void cancelMaintenancePlanById(Integer id);

}
