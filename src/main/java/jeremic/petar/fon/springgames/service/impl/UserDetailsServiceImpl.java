package jeremic.petar.fon.springgames.service.impl;

import jeremic.petar.fon.springgames.entity.User;
import jeremic.petar.fon.springgames.repository.UserRepository;
import jeremic.petar.fon.springgames.security.user.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + s + " not found."));
        UserDetails result = new UserPrincipal(user);
        return result;
    }
}
