package br.udesc.smartain.restsmartainproject.domain.glo.UserComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    @Query(
            value = "SELECT * FROM glo.tbgrupousuario g WHERE g.grucodigo = ?1",
            nativeQuery = true
    )
    public Optional<UserGroup> findGroupById(Short groupId);

    @Query(
            value = "SELECT * FROM glo.tbgrupousuario g ORDER BY g.grucodigo",
            nativeQuery = true)
    public Optional<List<UserGroup>> findAllGroups();


}
