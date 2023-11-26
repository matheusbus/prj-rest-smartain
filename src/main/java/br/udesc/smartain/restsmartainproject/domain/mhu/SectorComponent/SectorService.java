package br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> findAll() {
        return sectorRepository.findAll();
    }

    public List<Sector> findAllByStatus(Short status) {
        return sectorRepository.findAllByStatus(status);
    }

    public Optional<Sector> findById(Integer id) {
        return sectorRepository.findById(id);
    }

    @Transactional
    public Sector save(Sector sector) {
        return sectorRepository.save(sector);
    }

    @Transactional
    public Object inactiveSector(Sector sectorToInactive) {
        if(sectorToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The Sector " + sectorToInactive.getId() + " is already inactive";
        }

        sectorRepository.inacivateSectorById(sectorToInactive.getId());
        return "Success! The Sector " + sectorToInactive.getId() + " has been inactived.";
    }

}
