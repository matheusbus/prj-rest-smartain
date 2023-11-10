package br.udesc.smartain.restsmartainproject.domain.mhu.RoleComponent;

import br.udesc.smartain.restsmartainproject.domain.mhu.RoleComponent.Role;
import br.udesc.smartain.restsmartainproject.domain.states.RegisterState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public List<Role> findAllByStatus(Short status) {
        return roleRepository.findAllByStatus(status);
    }

    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Transactional
    public Role save(Role Role) {
        return roleRepository.save(Role);
    }

    @Transactional
    public Object inactiveRole(Role RoleToInactive) {
        if(RoleToInactive.getStatus().equals(RegisterState.INACTIVE)) {
            return "The Role " + RoleToInactive.getId() + " is already inactive";
        }

        roleRepository.inacivateRoleById(RoleToInactive.getId());
        return "Success! The Role " + RoleToInactive.getId() + " has been inactived.";
    }

}
