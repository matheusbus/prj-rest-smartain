package br.udesc.smartain.restsmartainproject.domain.glo.UserComponent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT * FROM glo.tbusuario u ORDER BY u.usucodigo",
            nativeQuery = true
    )
    public Optional<List<User>> findAllUsers();

    @Query(
            value = "SELECT * FROM glo.tbusuario u WHERE u.usustatus = 1",
            nativeQuery = true)
    public Optional<List<User>> findAllActiveUsers();

    @Query(
            value = "SELECT * FROM glo.tbusuario u WHERE u.usustatus = ?1",
            nativeQuery = true
    )
    public Optional<List<User>> findUsersByStatus(Integer status);

    @Query(
            value = "SELECT * FROM glo.tbusuario u WHERE u.usulogin = login",
            nativeQuery = true)
    public Optional<User> findUserByLogin(String login);

    @Query(
            value = "SELECT * FROM glo.tbusuario u ORDER BY u.usucodigo \n-- #pageable\n",
            countQuery = "SELECT COUNT(*) FROM glo.tbusuario",
            nativeQuery = true)
    public Optional<Page<User>> findAllUsersWithPagination(Pageable pageable);

    @Query(
            value = "SELECT * FROM glo.tbusuario u WHERE u.grucodigo = ?1",
            nativeQuery = true
    )
    public Optional<List<User>> findUsersByGroup(Short groupId);

}
