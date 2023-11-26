package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceSolicitationService {

    @Autowired
    private ServiceSolicitationRepository serviceSolicitationRepository;

    public List<ServiceSolicitation> findAll() {
        return serviceSolicitationRepository.findAll();
    }

    public List<ServiceSolicitation> findAllByStatus(Short status) {
        return serviceSolicitationRepository.findAllByStatus(status);
    }

    public Optional<ServiceSolicitation> findById(Integer id) {
        return serviceSolicitationRepository.findById(id);
    }

    @Transactional
    public ServiceSolicitation save(ServiceSolicitation serviceSolicitation) {
        return serviceSolicitationRepository.save(serviceSolicitation);
    }

    @Transactional
    public Object denyServiceSolicitation(ServiceSolicitation serviceSolicitation) {
        if(serviceSolicitation.getStatus().equals(ServiceSolicitationStatus.DENIED)) {
            return "The Service Solicitation " + serviceSolicitation.getId() + " is already denied.";
        }

        serviceSolicitationRepository.denyServiceSolicitationById(serviceSolicitation.getId());
        return "Success! The Service Solicitation " + serviceSolicitation.getId() + " has been denied.";
    }

    @Transactional
    public Object approveServiceSolicitation(ServiceSolicitation serviceSolicitation) {
        if(serviceSolicitation.getStatus().equals(ServiceSolicitationStatus.APPROVED)) {
            return "The Service Solicitation " + serviceSolicitation.getId() + " is already approved.";
        }

        serviceSolicitationRepository.denyServiceSolicitationById(serviceSolicitation.getId());
        return "Success! The Service Solicitation " + serviceSolicitation.getId() + " has been approved.";
    }

    @Transactional
    public Object cancelServiceSolicitation(ServiceSolicitation serviceSolicitation) {
        if(serviceSolicitation.getStatus().equals(ServiceSolicitationStatus.CANCELED)) {
            return "The Service Solicitation " + serviceSolicitation.getId() + " is already canceled.";
        }

        serviceSolicitationRepository.cancelServiceSolicitationById(serviceSolicitation.getId());
        return "Success! The Service Solicitation " + serviceSolicitation.getId() + " has been canceled.";
    }

}
