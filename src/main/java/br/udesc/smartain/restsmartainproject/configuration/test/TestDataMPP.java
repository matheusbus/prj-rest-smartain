package br.udesc.smartain.restsmartainproject.configuration.test;

import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnit;
import br.udesc.smartain.restsmartainproject.domain.glo.ManufacturingUnitComponent.ManufacturingUnitService;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.User;
import br.udesc.smartain.restsmartainproject.domain.glo.UserComponent.UserService;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.Machine;
import br.udesc.smartain.restsmartainproject.domain.mhu.MachineComponent.MachineService;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProfessionalComponent.Professional;
import br.udesc.smartain.restsmartainproject.domain.mhu.ProfessionalComponent.ProfessionalService;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent.MaintenancePlan;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent.MaintenancePlanService;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenancePlanComponent.MaintenancePlanStatus;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceType;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceTypeService;
import br.udesc.smartain.restsmartainproject.domain.mpp.OrderGenerationTypeComponent.OrderGenerationType;
import br.udesc.smartain.restsmartainproject.domain.mpp.OrderGenerationTypeComponent.OrderGenerationTypeService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceCauseComponent.ServiceCause;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceCauseComponent.ServiceCauseService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceInterventionComponent.ServiceIntervention;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceInterventionComponent.ServiceInterventionService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrder;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrderService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceOrderComponent.ServiceOrderStatus;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent.ServicePriority;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServicePriorityComponent.ServicePriorityService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent.ServiceSolicitation;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent.ServiceSolicitationService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSolicitationComponent.ServiceSolicitationStatus;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptom;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptomService;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
@Profile(value = "test")
public class TestDataMPP implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private ServiceInterventionService serviceInterventionService;

    @Autowired
    private ServiceSymptomService serviceSymptomService;

    @Autowired
    private MaintenanceTypeService maintenanceTypeService;

    @Autowired
    private ServiceCauseService serviceCauseService;

    @Autowired
    private ServicePriorityService servicePriorityService;

    @Autowired
    private OrderGenerationTypeService orderGenerationTypeService;

    @Autowired
    private ProfessionalService professionalService;

    @Autowired
    private ServiceSolicitationService serviceSolicitationService;

    @Autowired
    private ManufacturingUnitService manufacturingUnitService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private ServiceOrderService serviceOrderService;

    @Autowired
    private MaintenancePlanService maintenancePlanService;


    @Override
    public void run(String... args) throws Exception {
        makeServiceInterventions();
        makeServiceSymptons();
        makeMaintenanceTypes();
        makeServiceCauses();
        makeServicePriorities();
        makeOrderGenerationTypes();
        makeProfessionals();
        makeServiceSolicitations();
        makeServiceOrders();
        makeMaintenancePlan(1, LocalDateTime.now(), MaintenancePlanStatus.NOT_STARTED, 1);
    }


    public void makeServiceInterventions() {
        List<ServiceIntervention> serviceInterventions = Arrays.asList(
                new ServiceIntervention(null, "Acoplado", "ACO", "Deve ser apontado quando a ação tomada se constituiu no acoplamento de partes de um sistema."),
                new ServiceIntervention(null, "Ajustado", "AJU", "Será apontado quando a ação foi um ajuste, regulação ou calibração, efetuada no equipamento e/ou componente."),
                new ServiceIntervention(null, "Alinhado", "ALI", "Será apontado quando a ação foi um alinhamento do equipamento e/ou componente."),
                new ServiceIntervention(null, "Apertado", "APE", "Deve ser apontado quando a ação corretiva se constitui em um aperto em determinado componente."),
                new ServiceIntervention(null, "Desacoplado", "DPD", "Deve ser apontado quando a ação foi o desacoplamento de um componente e/ou equipamento."),
                new ServiceIntervention(null, "Fabricado", "FAB", "Apontará tal intervenção, quando a OM tratar da fabricação de uma peça."),
                new ServiceIntervention(null, "Fixado", "FIX", "Deve ser apontado quando foi efetuada a fixação de um determinado componente e/ou equipamento."),
                new ServiceIntervention(null, "Inspecionado", "INS", "Deve ser apontado quando da execução de uma inspeção."),
                new ServiceIntervention(null, "Instalado", "INT", "Deve ser apontado quando foi efetuada a instalação de um determinado componente e/ou equipamento, pela primeira vez, ou seja, o mesmo não existia na estrutura."),
                new ServiceIntervention(null, "Limpeza", "LIM", "Apontado quando efetuada limpeza do componente e/ou equipamento."),
                new ServiceIntervention(null, "Lubrificado", "LUB", "Apontado quando efetuada lubrificação, troca ou complementação de lubrificante."),
                new ServiceIntervention(null, "Modificado", "MOD", "Deve ser apontado quando a ação consistir em uma modificação (alteração), do projeto anterior do equipamento."),
                new ServiceIntervention(null, "Rearmado", "REA", "Deve ser apontado quando a ação foi reenergização do equipamento."),
                new ServiceIntervention(null, "Recuperado", "REC", "Deve ser apontado quando foi recuperado um determinado equipamento e/ou componente, reutilizando-o."),
                new ServiceIntervention(null, "Reposto", "REP", "Apontado quando da reposição de um componente no equipamento, que se encontrava operando sem o mesmo."),
                new ServiceIntervention(null, "Retirado", "RET", "Deve ser apontado quando da ação de remoção de um determinado elemento da estrutura, sendo o mesmo pertencente a ela, ou não."),
                new ServiceIntervention(null, "Soldado", "SOL", "Apontado quando da execução de uma solda em um determinado equipamento e/ou componente."),
                new ServiceIntervention(null, "Substituído", "SBS", "Apontado quando efetuada a troca do equipamento, ou de um componente do mesmo.")
        );
        serviceInterventionService.saveAll(serviceInterventions);
    }

    public void makeServiceSymptons() {
        List<ServiceSymptom> serviceSymptons = Arrays.asList(
                new ServiceSymptom(null, "Aberto", "ABE", "Será apontado quando o efeito constituir na descontinuidade em um circuito fechado."),
                new ServiceSymptom(null, "Baixo Rendimento", "BXR", "Deve ser apontado, quando o efeito se constituiu na queda no rendimento do maquinário, como por exemplo um equipamento levando 1 minuto para efetuar uma tarefa, quando normalmente a faria em 45 segundos."),
                new ServiceSymptom(null, "Desarmado", "DAR", "Será apontado quando o equipamento não estiver recebendo alimentação de sua fonte de energia."),
                new ServiceSymptom(null, "Despressurizado", "DPR", "Deve-se apontar tal sintoma, quando a pressão no equipamento e/ou na linha de alimentação estiver aquém do necessário para a operação normal."),
                new ServiceSymptom(null, "Empenado", "EMP", "Será apontado quando o dano no equipamento e/ou estrutura se constitua em um empeno, ou seja, uma alteração em seu corpo, impossibilitando seu ajuste com outro equipamento ou elemento."),
                new ServiceSymptom(null, "Queimado", "QMD", "Deve ser apontado quando o efeito consiste na queima do equipamento ou componente."),
                new ServiceSymptom(null, "Rompido", "ROP", "Será utilizado quando o dano no equipamento provocar separação entre suas partes, que deveríam ser contínuas."),
                new ServiceSymptom(null, "Ruído Anormal", "RAN", "Será apontado quando o equipamento apresentar ruído fora de sua normalidade."),
                new ServiceSymptom(null, "Sem Freio", "SFR", "Deve ser apontado, quando há impossibilidade de diminuir a velocidade de um equipamento até sua parada total, por não funcionamento do sistema de freio."),
                new ServiceSymptom(null, "Sem Velocidade", "SVL", "Deve ser apontado quando o equipamento não consegue chegar ao valor necessário de velocidade, para sua operação."),
                new ServiceSymptom(null, "Solto", "SLT", "Será apontado quando o efeito se constitui na inexistência de união, entre componentes que deveríam se encontrar interligados."),
                new ServiceSymptom(null, "Sujo", "SJO", "Deve ser apontado quando o efeito for a existência de sujeira, que comprometa o funcionamento do equipamento."),
                new ServiceSymptom(null, "Travado", "TRV", "Será apontado quando o equipamento se apresentar travado, ou seja, sem movimento algum."),
                new ServiceSymptom(null, "Trincado", "TRI", "Será apontado quando o dano no equipamento se constituir em uma trinca, ou seja, uma fissura visível."),
                new ServiceSymptom(null, "Vazando", "VAZ", "Deve ser apontado quando o efeito constitui um vazamento constante no equipamento."),
                new ServiceSymptom(null, "Vibrando", "VIB", "Deve ser apontado quando o efeito constitui uma vibração mecânica, acima do normal no equipamento."),
                new ServiceSymptom(null, "Preventiva", "PRE", "Será indicada quando a intervenção for proveniente de uma preventiva.")
        );
        serviceSymptomService.saveAll(serviceSymptons);
    }

    public void makeMaintenanceTypes() {
        List<MaintenanceType> maintenanceTypes = Arrays.asList(
            new MaintenanceType(null, "Manutenção preventiva "),
            new MaintenanceType(null, "Manutenção corretiva "),
            new MaintenanceType(null, "Manutenção preditiva"),
            new MaintenanceType(null, "Manutenção prescritiva")
        );
        maintenanceTypeService.saveAll(maintenanceTypes);
    }

    public void makeServiceCauses() {
        List<ServiceCause> serviceCauses = Arrays.asList(
                new ServiceCause(null, "Defeito de Fábrica", "DEF", "Quando da ocorrência de uma imperfeição, defeito de fabricação do componente/elemento responsável pela falha. Exemplos: Bobina com número menor de espiras, PLC com problemas em seu programa instalado na fábrica, etc."),
                new ServiceCause(null, "Desalinhamento", "DES", "Sem alinho, componente/elemento fora do seu devido alinhamento."),
                new ServiceCause(null, "Desnivelamento", "DEN", "Componente/elemento fora do nivelamento, ocasionando diferença de nível entre elementos que interagem."),
                new ServiceCause(null, "Falta de Proteção", "FPR", "Será indicado quando o efeito foi causado por uma falta de proteção, que deveria existir para salvaguardar um equipamento, bem como a retirada intencional de tal proteção. Exemplos: Queima de equipamento eletroeletrônico por falta de um disjuntor no circuito. Queima de uma fonte por um transiente de tensão devido à falta de um fusível tipo fio de proteção."),
                new ServiceCause(null, "Desregulamento", "DER", "Ocorre quando o efeito é proveniente de uma falta de ajuste, calibração, regulagem de um determinado componente/elemento e/ou equipamento. Exemplo: Capacitor variável da placa de corrente de balança fora do ajuste de operação."),
                new ServiceCause(null, "Destensionado", "DET", "Deve ser apontado quando o componente/elemento se encontrar sem tensionamento ou torque, necessário (recomendável) para sua operação."),
                new ServiceCause(null, "Engripado", "ENG", "Ocorre quando o componente/elemento responsável pela falha estiver com suas partes móveis, sem nenhuma mobilidade, devido a um alto coeficiente de atrito, proveniente de grande quantidade de oxidação (ferrugem), etc., "),
                new ServiceCause(null, "Fadiga", "FAD", "Quando o componente/elemento responsável pela falha se encontrava sob efeito de afadigamento, cansaço, ou seja, com fadiga que consiste na diminuição gradual da resistência de um material por efeito de solicitações repetidas."),
                new ServiceCause(null, "Fissura", "FIS", "Quando o componente/elemento ocasionador do efeito tem ou tinha fissuras em seu corpo, ou seja, fendas, cisuras ou incisuras."),
                new ServiceCause(null, "Folga", "FOL", "Componente/elemento ocasionador com folga, ou seja, espaço entre partes de interação acima do permitido."),
                new ServiceCause(null, "Fora de Especificação", "FOE", "Ocorre quando o componente/elemento ocasionador do efeito se encontra fora da especificação estabelecida para o trabalho. Exemplos: Resistor de 2K2Q no lugar de um resistor que deveria ser de 3KQ. Rolamento blindado (zz) no lugar de um rolamento que deveria ser tipo aberto."),
                new ServiceCause(null, "Gasto", "GAS", "Componente/elemento deteriorado, consumido nas partes úteis de seu corpo."),
                new ServiceCause(null, "Não Identificada", "NID", "Devemos apontar tal causa quando não pudermos afirmar com exatidão o que provocou o efeito."),
                new ServiceCause(null, "Nível Baixo", "NBX", "Esta causa se refere diretamente a lubrificação, ou seja, o motivo da intervenção é o nível aquém do normal de lubrificante, em um determinado equipamento. Exemplo: Rolamento com vibração excessiva em alta freqüência."),
                new ServiceCause(null, "Preventiva", "PRE", "Será indicada tal causa quando a intervenção for proveniente de uma preventiva, ou seja, de um plano de manutenção."),
                new ServiceCause(null, "Preditiva", "PRD", "Será indicada tal causa quando a intervenção for um exame preditivo, das condições de um determinado subconjunto. "),
                new ServiceCause(null, "Rompido", "ROP", "Será apontada tal causa, quando o componente/elemento tiver se rompido, ou seja, interropida sua continuidade estrutural. Exemplo: Rasgo na correia transportadora de minério."),
                new ServiceCause(null, "Rota de Inspeção", "RTI", "Será indicada tal causa quando a intervenção tiver como objetivo/causa realizar inspeção em um equipamento ou em vários, dispostos em uma rota."),
                new ServiceCause(null, "Sobrecarga de Peso", "SPE", "Tal causa deve ser indicada, quando o efeito decorrer de uma solicitação ao equipamento além de sua capacidade máxima de suportar peso."),
                new ServiceCause(null, "Sobrecarga de Tensão", "STE", "Será indicada tal causa quando a falha for proveniente de uma sobrecarga de tensão acima do normal, mesmo tendo o equipamento uma proteção."),
                new ServiceCause(null, "Sobrecarga de Corrente", "SCO", "Será indicada tal causa quando a falha for proveniente de uma sobrecarga de corrente acima do normal, mesmo tendo o equipamento uma proteção."),
                new ServiceCause(null, "Subdimensionado", "SUB", "Tal causa será indicada, quando o motivo do efeito for a estipulação no projeto de componente que não atende os requisitos mínimos para o bom funcionamento do conjunto. Exemplo-, O projeto determina um disjuntor de proteção ao circuito, de 4A /220V, sendo a real necessidade um de 5A/220V. ")
        );
        serviceCauseService.saveAll(serviceCauses);
    }

    public void makeServicePriorities() {
        List<ServicePriority> servicePriorities = Arrays.asList(
                new ServicePriority(null, "Baixa"),
                new ServicePriority(null, "Média"),
                new ServicePriority(null, "Alta")
        );
        servicePriorityService.saveAll(servicePriorities);
    }

    public void makeOrderGenerationTypes() {
        List<OrderGenerationType> orderGenerationTypes = Arrays.asList(
                new OrderGenerationType(null, "Gerada a partir do Plano de Manutenção"),
                new OrderGenerationType(null, "Gerada a partir do executante (emergência)"),
                new OrderGenerationType(null, "Gerada a partir da Solicitação de Serviço"),
                new OrderGenerationType(null, "Gerada a partir de inspeção")
        );
        orderGenerationTypeService.saveAll(orderGenerationTypes);
    }

    public void makeProfessionals() {
        User user = userService.findById(1).get();
        List<Professional> professionals = Arrays.asList(
            new Professional(null, "João Francisco", LocalDate.of(1988, Month.FEBRUARY, 12), LocalDateTime.now(), "29175183912", RegisterState.ACTIVE, user),
            new Professional(null, "Cléber Maurício", LocalDate.of(1996, Month.DECEMBER, 26), LocalDateTime.now(), "49842849713", RegisterState.ACTIVE, user)
        );
        for(Professional professional : professionals) {
            professionalService.save(professional);
        }
    }

    public void makeServiceSolicitations() {
        ServiceSolicitation newServiceSolicitation = new ServiceSolicitation();

        ManufacturingUnit unit = manufacturingUnitService.findById(1).get();
        Machine machine = machineService.findById(1).get();
        ServicePriority priority = servicePriorityService.findById(1).get();
        ServiceSymptom symptom = serviceSymptomService.findById(8).get();
        User user = userService.findById(1).get();
        MaintenanceType maintenanceType = maintenanceTypeService.findById(2).get();

        newServiceSolicitation.setUnit(unit);
        newServiceSolicitation.setMachine(machine);
        newServiceSolicitation.setDescription("Máquina apresenta barulhos estranhos. Deve ser verificado a porta traseira, pois parecia ser lá o problema.");
        newServiceSolicitation.setOpeningDate(LocalDateTime.now());
        newServiceSolicitation.setStatus(ServiceSolicitationStatus.OPENED);
        newServiceSolicitation.setPriority(priority);
        newServiceSolicitation.setSymptom(symptom);
        newServiceSolicitation.setResponsibleUser(user);
        newServiceSolicitation.setMaintenanceType(maintenanceType);

        serviceSolicitationService.save(newServiceSolicitation);
    }

    public ServiceOrder makeServiceOrder(Integer unitId, Integer machineId, Integer serviceSolicitationId, Integer serviceInterventionId, Integer serviceCauseId, Integer orderGenerationTypeId, Integer maintenancePlanId, Integer userId, LocalTime estimatedDuration, ServiceOrderStatus status, Integer maintenanceTypeId, LocalDateTime openingDate, Integer priorityId) {
        ServiceOrder serviceOrder = new ServiceOrder();

        ManufacturingUnit unit = manufacturingUnitService.findById(unitId).get();
        serviceOrder.setUnit(unit);

        Machine machine = machineService.findById(machineId).get();
        serviceOrder.setMachine(machine);

        MaintenanceType maintenanceType = maintenanceTypeService.findById(maintenanceTypeId).get();
        ServicePriority priority = servicePriorityService.findById(priorityId).get();
        if(orderGenerationTypeId == 3) {
            ServiceSolicitation serviceSolicitation = serviceSolicitationService.findById(serviceSolicitationId).get();
            serviceOrder.setServiceSolicitation(serviceSolicitation);
            serviceOrder.setMaintenanceType(serviceSolicitation.getMaintenanceType());
            serviceOrder.setPriority(serviceSolicitation.getPriority());
        } else {
            serviceOrder.setServiceSolicitation(null);
            serviceOrder.setMaintenanceType(maintenanceType);
            serviceOrder.setPriority(priority);
        }

        if(serviceInterventionId != null) {
            Optional<ServiceIntervention> serviceIntervention = serviceInterventionService.findById(serviceInterventionId);
            serviceOrder.setServiceIntervention(serviceIntervention.orElse(null));
        }

        if(serviceCauseId != null) {
            ServiceCause cause = serviceCauseService.findById(serviceCauseId).get();
            serviceOrder.setServiceCause(cause);
        }

        OrderGenerationType orderGenerationType = orderGenerationTypeService.findById(orderGenerationTypeId).get();
        serviceOrder.setGenerationType(orderGenerationType);

        User user = userService.findById(userId).get();
        serviceOrder.setOpeningUser(user);

        if(maintenancePlanId != null) {
            MaintenancePlan maintenancePlan = maintenancePlanService.findById(maintenancePlanId).get();
            serviceOrder.setMaintenancePlan(maintenancePlan);
        }

        serviceOrder.setEstimatedDuration(estimatedDuration);
        serviceOrder.setStatus(status);
        serviceOrder.setOpeningDate(openingDate);

        ServiceOrder serviceOrderSaved = serviceOrderService.save(serviceOrder);
        return serviceOrderSaved;
    }

    public void makeServiceOrders() {
        makeServiceOrder(1, 1, null, 18, 17, 2, null, 1, LocalTime.of(3, 30), ServiceOrderStatus.COMPLETED, 2, LocalDateTime.of(2023, Month.NOVEMBER, 26, 17, 30, 0), 3);
        makeServiceOrder(1, 1, null, 11, 15, 4, null, 1, LocalTime.of(6, 0), ServiceOrderStatus.COMPLETED, 1, LocalDateTime.of(2023, Month.NOVEMBER, 26, 14, 18, 0), 1);
        makeServiceOrder(1, 1, 1, null, null, 3, null, 1, LocalTime.of(2, 0), ServiceOrderStatus.NOT_STARTED, 2, LocalDateTime.of(2023, Month.NOVEMBER, 27, 19, 15, 0), 2);
        makeServiceOrder(1, 1, null, null, null, 2, null, 1, LocalTime.of(1, 0), ServiceOrderStatus.IN_PROGRESS, 2, LocalDateTime.of(2023, Month.NOVEMBER, 27, 8, 46, 0), 2);
        makeServiceOrder(1, 1, null, null, null, 2, null, 1, LocalTime.of(0, 30), ServiceOrderStatus.IN_PROGRESS, 2, LocalDateTime.of(2023, Month.NOVEMBER, 27, 13, 50, 0), 3);
        makeServiceOrder(1, 1, null, null, null, 2, null, 1, LocalTime.of(0, 50), ServiceOrderStatus.SUSPENDED, 2, LocalDateTime.of(2023, Month.NOVEMBER, 26, 17, 30, 0), 1);
        makeServiceOrder(1, 1, null, null, null, 2, null, 1, LocalTime.of(2, 45), ServiceOrderStatus.SUSPENDED, 2, LocalDateTime.of(2023, Month.NOVEMBER, 25, 15, 17, 0), 2);

    }

    public void makeMaintenancePlan(Integer unitId, LocalDateTime createdDate, MaintenancePlanStatus status, Integer userId) {
        MaintenancePlan maintenancePlan = new MaintenancePlan();

        ManufacturingUnit unit = manufacturingUnitService.findById(unitId).get();
        maintenancePlan.setUnit(unit);
        maintenancePlan.setName("Plano Inicial");
        maintenancePlan.setCreatedDate(createdDate);
        maintenancePlan.setStatus(status);

        User user = userService.findById(userId).get();
        maintenancePlan.setUser(user);

        List<ServiceOrder> orders = Arrays.asList(
                makeServiceOrder(1, 1, null, null, null, 1, null, 1, LocalTime.of(0, 30), ServiceOrderStatus.NOT_STARTED, 1, LocalDateTime.of(2023, Month.NOVEMBER, 27, 8, 0, 0), 1),
                makeServiceOrder(1, 1, null, null, null, 1, null, 1, LocalTime.of(0, 30), ServiceOrderStatus.NOT_STARTED, 1, LocalDateTime.of(2023, Month.DECEMBER, 4, 8, 0, 0), 1),
                makeServiceOrder(1, 1, null, null, null, 1, null, 1, LocalTime.of(0, 30), ServiceOrderStatus.NOT_STARTED, 1, LocalDateTime.of(2023, Month.DECEMBER, 11, 8, 0, 0), 1),
                makeServiceOrder(1, 1, null, null, null, 1, null, 1, LocalTime.of(0, 30), ServiceOrderStatus.NOT_STARTED, 1, LocalDateTime.of(2023, Month.DECEMBER, 18, 8, 0, 0), 1),
                makeServiceOrder(1, 1, null, null, null, 1, null, 1, LocalTime.of(0, 30), ServiceOrderStatus.NOT_STARTED, 1, LocalDateTime.of(2023, Month.DECEMBER, 25, 8, 0, 0), 1),
                makeServiceOrder(1, 1, null, null, null, 1, null, 1, LocalTime.of(0, 30), ServiceOrderStatus.NOT_STARTED, 1, LocalDateTime.of(2023, Month.JANUARY, 1, 8, 0, 0), 1),
                makeServiceOrder(1, 1, null, null, null, 1, null, 1, LocalTime.of(0, 30), ServiceOrderStatus.NOT_STARTED, 1, LocalDateTime.of(2023, Month.DECEMBER, 8, 8, 0, 0), 1)
        );
        maintenancePlanService.save(maintenancePlan);

        for(ServiceOrder serviceOrder : orders) {
            serviceOrderService.save(serviceOrder);
            maintenancePlan.addServiceOrder(serviceOrder);
        }

        maintenancePlanService.save(maintenancePlan);
    }

}
