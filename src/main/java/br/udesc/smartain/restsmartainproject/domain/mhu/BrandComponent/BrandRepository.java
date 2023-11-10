package br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query(
            value = "SELECT * FROM mhu.tbmarca WHERE tbmarca.marstatus = ?1",
            nativeQuery = true
    )
    public List<Brand> findAllByStatus(Short status);

    @Modifying
    @Query(
            value = "UPDATE mhu.tbmarca SET tbmarca.marstatus = 2 WHERE tbmarca.marcodigo = ?1",
            nativeQuery = true
    )
    public void inacivateBrandById(Integer id);

}
