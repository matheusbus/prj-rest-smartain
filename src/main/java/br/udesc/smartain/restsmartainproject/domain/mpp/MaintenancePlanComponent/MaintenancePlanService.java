package br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenancePlanService {

    @Autowired
    private MaintenancePlanRepository maintenancePlanRepository;

    public List<MaintenancePlan> findAll() {
        return maintenancePlanRepository.findAll();
    }

    public List<MaintenancePlan> findAllByStatus(Short status) {
        return maintenancePlanRepository.findAllByStatus(status);
    }

    public Optional<MaintenancePlan> findById(Integer id) {
        return maintenancePlanRepository.findById(id);
    }

    @Transactional
    public MaintenancePlan save(MaintenancePlan maintenancePlan) {
        return maintenancePlanRepository.save(maintenancePlan);
    }

    @Transactional
    public Object initMaintenancePlan(MaintenancePlan maintenancePlan) {
        if(maintenancePlan.getStatus().equals(MaintenancePlanStatus.IN_PROGRESS)) {
            return "The Maintenance Plan " + maintenancePlan.getId() + " is already in progress.";
        }

        maintenancePlanRepository.initMaintenancePlanById(maintenancePlan.getId());
        return "Success! The Maintenance Plan " + maintenancePlan.getId() + " has been initialized.";
    }

    @Transactional
    public Object completeMaintenancePlan(MaintenancePlan maintenancePlan) {
        if(maintenancePlan.getStatus().equals(MaintenancePlanStatus.COMPLETED)) {
            return "The Maintenance Plan " + maintenancePlan.getId() + " is already completed.";
        }

        maintenancePlanRepository.completeMaintenancePlanById(maintenancePlan.getId());
        return "Success! The Maintenance Plan " + maintenancePlan.getId() + " has been completed.";
    }

    @Transactional
    public Object cancelMaintenancePlan(MaintenancePlan maintenancePlan) {
        if(maintenancePlan.getStatus().equals(MaintenancePlanStatus.CANCELED)) {
            return "The Maintenance Plan " + maintenancePlan.getId() + " is already in canceled.";
        }

        maintenancePlanRepository.cancelMaintenancePlanById(maintenancePlan.getId());
        return "Success! The Maintenance Plan " + maintenancePlan.getId() + " has been canceled.";
    }

}
