package cl.streamlink.contact.security;

import cl.streamlink.contact.domain.User;
import cl.streamlink.contact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findOneByEmail(email).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + email + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(email)//
                .password(user.getPassword())//
                .authorities(user.getRoles())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

}
