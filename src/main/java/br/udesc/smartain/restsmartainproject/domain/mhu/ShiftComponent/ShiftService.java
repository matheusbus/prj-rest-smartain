package br.udesc.smartain.restsmartainproject.domain.mhu.ShiftComponent;

import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public List<Shift> findAll() {
        return shiftRepository.findAll();
    }

    public List<Shift> findAllByStatus(Short status) {
        return shiftRepository.findAllByStatus(status);
    }

    public Optional<Shift> findById(Integer id) {
        return shiftRepository.findById(id);
    }

    @Transactional
    public Shift save(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Transactional
    public Object inactiveShift(Shift shiftToInactive) {
        if(shiftToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The shift " + shiftToInactive.getId() + " is already inactive";
        }

        shiftRepository.inacivateShiftById(shiftToInactive.getId());
        return "Success! The shift " + shiftToInactive.getId() + " has been inactived.";
    }

}
