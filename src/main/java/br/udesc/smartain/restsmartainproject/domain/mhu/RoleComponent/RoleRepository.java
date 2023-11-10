package br.udesc.smartain.restsmartainproject.domain.mhu.RoleComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbcargo WHERE tbcargo.carstatus = ?1",
            nativeQuery = true
    )
    public List<Role> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbcargo SET tbcargo.carstatus = 2 WHERE tbcargo.carcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateRoleById(Integer id);

}
