package br.udesc.smartain.restsmartainproject.configuration.test;

import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceType;
import br.udesc.smartain.restsmartainproject.domain.mpp.MaintenanceTypeComponent.MaintenanceTypeService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceCauseComponent.ServiceCause;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceCauseComponent.ServiceCauseService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceInterventionComponent.ServiceIntervention;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceInterventionComponent.ServiceInterventionService;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptom;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceSymptomComponent.ServiceSymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile(value = "test")
public class TestDataMPP implements CommandLineRunner {

    @Autowired
    private ServiceInterventionService serviceInterventionService;

    @Autowired
    private ServiceSymptomService serviceSymptomService;

    @Autowired
    private MaintenanceTypeService maintenanceTypeService;

    @Autowired
    private ServiceCauseService serviceCauseService;


    @Override
    public void run(String... args) throws Exception {
        makeServiceInterventions();
        makeServiceSymptons();
        makeMaintenanceTypes();
        makeServiceCauses();
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

}
