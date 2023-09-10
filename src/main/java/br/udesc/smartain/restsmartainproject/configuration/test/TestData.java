package br.udesc.smartain.restsmartainproject.configuration.test;

import br.udesc.smartain.restsmartainproject.domain.model.glo.User;
import br.udesc.smartain.restsmartainproject.domain.model.glo.UserGroup;
import br.udesc.smartain.restsmartainproject.domain.model.states.RegisterDataState;
import br.udesc.smartain.restsmartainproject.domain.service.glo.UserGroupService;
import br.udesc.smartain.restsmartainproject.domain.service.glo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile(value = "test")
public class TestData implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserGroupService userGroupService;

    @Override
    public void run(String... args) throws Exception {
        makeUsers(makeUserGroups());
    }

    public void makeUsers(List<UserGroup> groups) {
        userService.saveAll(
                Arrays.asList(
                        new User(null, groups.get(0), "matheusbus", "matheus", "matheus@smartain.com.br", LocalDateTime.now(), LocalDateTime.now(), RegisterDataState.ACTIVE)
                )
        );
    }

    public List<UserGroup> makeUserGroups(){
        List<UserGroup> userGroups = Arrays.asList(
                new UserGroup(null, "Gestores de Manutenção", "Grupo que abrange os usuários gestores, que possuem acesso total ao sistema, priorizando os indicadores e relatórios.", RegisterDataState.ACTIVE),
                new UserGroup(null, "Coordenadores de Manutenção", "Grupo que abrange os usuários coordenadores, com privilégios de cadastros e manuseio de integrações.", RegisterDataState.ACTIVE),
                new UserGroup(null, "Planejadores e Controladores de Manutenção", "Grupo que abrange os usuários responsáveis pelos Planos de Manutenção, cronograma, e dados necessários pela manutenção.", RegisterDataState.ACTIVE),
                new UserGroup(null, "Planejadores e Controladores de Produção", "Grupo que abrange os usuários da produção, que terão acesso ao sistema para visualizar o cronograma de manutenção e gerar novas solicitações de serviço.", RegisterDataState.ACTIVE),
                new UserGroup(null, "Operadores de Manutenção", "Grupo que abrange os usuários manutentores, que serão responsáveis por executar ordens de serviço.", RegisterDataState.ACTIVE)
        );

        userGroupService.saveAll(userGroups);
        return userGroups;
    }
}
