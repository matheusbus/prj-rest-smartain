package br.udesc.smartain.restsmartainproject.domain.service.glo;

import br.udesc.smartain.restsmartainproject.domain.model.glo.User;
import br.udesc.smartain.restsmartainproject.domain.repository.glo.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Optional<List<User>> findAllUsers(){
        return userRepository.findAllUsers();
    }

    @Transactional
    public void saveUser(@Valid User user){
        userRepository.save(user);
    }

    @Transactional
    public void saveAll(@Valid List<User> users){
        userRepository.saveAll(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
