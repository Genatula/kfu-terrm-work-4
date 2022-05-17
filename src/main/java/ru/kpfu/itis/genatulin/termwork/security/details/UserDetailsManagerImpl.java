package ru.kpfu.itis.genatulin.termwork.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.genatulin.termwork.models.User;
import ru.kpfu.itis.genatulin.termwork.repositories.UserRepository;

@Service
public class UserDetailsManagerImpl implements UserDetailsManager {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsManagerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserDetails user) {
        UserDetailsImpl userDetails = (UserDetailsImpl) user;
        userRepository.save(userDetails.getUser());
    }

    @Override
    public void updateUser(UserDetails user) {
        UserDetailsImpl userDetails = (UserDetailsImpl) user;
        userRepository.save(userDetails.getUser());
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteUserByUsername(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        User user = userRepository.getCurrentUser();
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException("User with such username does not exist");
        }
        User user = userRepository.getUserByUsername(username);
        return new UserDetailsImpl(user);
    }
}
