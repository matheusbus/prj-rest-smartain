package br.udesc.smartain.restsmartainproject.domain.mhu.AlertComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.MachineService;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private MachineService machineService;

    public List<Alert> findAll() {
        return alertRepository.findAll();
    }

    public List<Alert> findAllByStatus(Short status) {
        return alertRepository.findAllByStatus(status);
    }

    public List<Alert> findAllByType(Short type) {
        return alertRepository.findAllByType(type);
    }

    public Optional<Alert> findById(Integer id) {
        return alertRepository.findById(id);
    }

    @Transactional
    public Alert save(Alert alert) {
        return alertRepository.save(alert);
    }

    @Transactional
    public void saveAll(List<Alert> alerts) {
        alertRepository.saveAll(alerts);
    }

    // Método para executar a cada 24 horas
    //@Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void checkMachinesWarranties() {
        List<Machine> machines = machineService.findAllByStatus(RegisterState.ACTIVE.getValue());
        machines = machines
                .stream()
                .filter(machine -> machine.getWarranty().equals((short) 1))
                .collect(Collectors.toList());

        for(Machine machine : machines) {
            checkMachineWarrantyAlert(machine);
        }
    }

    private void checkMachineWarrantyAlert(Machine machine) {
        LocalDate currentDate = LocalDate.now();
        LocalDate warrantyExpirationDate = machine.getWarrantyExpDate();
        long daysRemaining = ChronoUnit.DAYS.between(currentDate, warrantyExpirationDate);

        if((daysRemaining == 30 || daysRemaining == 7) && (!alertAlreadyIssued())) {
            issueAlert(machine, (int) daysRemaining);
        }
    }

    private boolean alertAlreadyIssued() {
        return false;
    }

    private void issueAlert(Machine machine, int dias) {
        Alert newWarrantyAlert = new Alert();

        newWarrantyAlert.setId(null);
        newWarrantyAlert.setTitle("Alerta de Garantia - Máquina: " + machine.getTag() + " - Restam " + dias + " dias.");
        newWarrantyAlert.setDescription(getWarrantyAlertDescription(machine, dias));
        newWarrantyAlert.setCreatedUser(null);
        newWarrantyAlert.setExpirationDate(LocalDate.now().plusDays(30));
        newWarrantyAlert.setStatus(AlertStatus.PENDING);
        newWarrantyAlert.setType(AlertType.ALERT_BY_WARRANTY);
        newWarrantyAlert.setMachine(machine);
        newWarrantyAlert.setPlan(null);
        newWarrantyAlert.setCreatedDate(LocalDateTime.now());

        save(newWarrantyAlert);
    }

    private String getWarrantyAlertDescription(Machine machine, int dias) {
        return new StringBuilder()
                .append("Atenção! Máquina: " + machine.getTag() + " (Modelo: " + machine.getModel().getModel() + ") ")
                .append("pertencente à célula " + machine.getProductionCell().getTag() + " (" + machine.getProductionCell().getName() + "), ")
                .append("setor " + machine.getSector().getTag() + " (" + machine.getSector().getName() + ") e ")
                .append("unidade fabril " + machine.getSector().getUnit().getTag() + " ")
                .append("está com período de garantia prestes a vencer! Restam " +dias + " dias de garantia.")
                .toString();
    }

    @Transactional
    public Object completeAlert(Alert alertToComplete) {
        if(alertToComplete.getStatus().equals(AlertStatus.ATTENDED)) {
            return "The Alert " + alertToComplete.getId() + " is already attended.";
        }

        alertRepository.completeAlertById(alertToComplete.getId());
        return "Success! The Alert " + alertToComplete.getId() + " has been attended.";
    }

}
