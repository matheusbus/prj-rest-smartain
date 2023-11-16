package br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.BrandComponent.Brand;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public List<Brand> findAllByStatus(Short status) {
        return brandRepository.findAllByStatus(status);
    }

    public Optional<Brand> findById(Integer id) {
        return brandRepository.findById(id);
    }

    @Transactional
    public Brand save(Brand Brand) {
        return brandRepository.save(Brand);
    }

    @Transactional
    public void saveAll(List<Brand> brands) {
        brandRepository.saveAll(brands);
    }

    @Transactional
    public Object inactiveBrand(Brand BrandToInactive) {
        if(BrandToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The Brand " + BrandToInactive.getId() + " is already inactive";
        }

        brandRepository.inacivateBrandById(BrandToInactive.getId());
        return "Success! The Brand " + BrandToInactive.getId() + " has been inactived.";
    }

}
