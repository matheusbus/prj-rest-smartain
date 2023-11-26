package br.udesc.smartain.restsmartainproject.domain.mhu.ProfessionalComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessionalRepository extends JpaRepository<Professional, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbprofissional WHERE tbprofissional.prfstatus = ?1",
            nativeQuery = true
    )
    public List<Professional> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbprofissional SET tbprofissional.prfstatus = 2 WHERE tbprofissional.prfcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateProfessionalById(Integer id);

}
