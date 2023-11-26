package br.udesc.smartain.restsmartainproject.configuration.test;

import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceInterventionComponent.ServiceIntervention;
import br.udesc.smartain.restsmartainproject.domain.mpp.ServiceInterventionComponent.ServiceInterventionService;
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


    @Override
    public void run(String... args) throws Exception {
        makeServiceInterventions();
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

}
