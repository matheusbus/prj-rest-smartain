package br.udesc.smartain.restsmartainproject.domain.glo.UserComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.udesc.smartain.restsmartainproject.domain.mhu.SectorComponent.Sector;

import java.util.List;
import java.util.Optional;

@Service
public class UserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Transactional(readOnly = true)
    public Optional<List<UserGroup>> findAllUserGroups() {
        return userGroupRepository.findAllGroups();
    }

    @Transactional(readOnly = true)
    public Optional<UserGroup> findById(Long id) {
        return userGroupRepository.findById(id);
    }

    @Transactional
    public void save(UserGroup userGroup) {
        userGroupRepository.save(userGroup);
    }

    @Transactional
    public void saveAll(List<UserGroup> userGroups) {
        userGroupRepository.saveAll(userGroups);
    }
}
