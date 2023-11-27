package br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepository;

    public List<ServiceOrder> findAll() {
        return serviceOrderRepository.findAll();
    }

    public List<ServiceOrder> findAllByStatus(Short status) {
        return serviceOrderRepository.findAllByStatus(status);
    }

    public Optional<ServiceOrder> findById(Integer id) {
        return serviceOrderRepository.findById(id);
    }

    @Transactional
    public ServiceOrder save(ServiceOrder serviceOrder) {
        return serviceOrderRepository.save(serviceOrder);
    }

    @Transactional
    public Object initServiceSolicitation(ServiceOrder serviceOrder) {
        if(serviceOrder.getStatus().equals(ServiceOrderStatus.IN_PROGRESS)) {
            return "The Service Order " + serviceOrder.getId() + " is already in progress.";
        }

        serviceOrderRepository.initServiceOrderById(serviceOrder.getId());
        return "Success! The Service Order " + serviceOrder.getId() + " has been initialized.";
    }

    @Transactional
    public Object scheduleServiceSolicitation(ServiceOrder serviceOrder) {
        if(serviceOrder.getStatus().equals(ServiceOrderStatus.SCHEDULED)) {
            return "The Service Order " + serviceOrder.getId() + " is already scheduled.";
        }

        serviceOrderRepository.scheduleServiceOrderById(serviceOrder.getId());
        return "Success! The Service Order " + serviceOrder.getId() + " has been scheduled.";
    }

    @Transactional
    public Object suspendServiceSolicitation(ServiceOrder serviceOrder) {
        if(serviceOrder.getStatus().equals(ServiceOrderStatus.SUSPENDED)) {
            return "The Service Order " + serviceOrder.getId() + " is already in suspended.";
        }

        serviceOrderRepository.suspendServiceOrderById(serviceOrder.getId());
        return "Success! The Service Order " + serviceOrder.getId() + " has been suspended.";
    }

    @Transactional
    public Object completeServiceSolicitation(ServiceOrder serviceOrder) {
        if(serviceOrder.getStatus().equals(ServiceOrderStatus.COMPLETED)) {
            return "The Service Order " + serviceOrder.getId() + " is already completed.";
        }

        serviceOrderRepository.completeServiceOrderById(serviceOrder.getId());
        return "Success! The Service Order " + serviceOrder.getId() + " has been completed.";
    }


}
