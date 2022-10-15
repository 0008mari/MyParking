package project.myparking.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.myparking.config.auth.dto.CustomUserDetails;
import project.myparking.domain.User;
import project.myparking.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // security 에서 제공하는  User 말고 그냥 domain User 로 사용

        User user = userRepository.findByUsernameContaining(username);
        CustomUserDetails userDetails = null;
        if (user != null) {
            userDetails = new CustomUserDetails();
            userDetails.setUser(user);
        } else{
            throw new UsernameNotFoundException("User not exist with name : " + username);
        }
        return userDetails;
    }


}
