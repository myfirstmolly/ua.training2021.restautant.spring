package ua.training.restaurant.service.impl;

import org.springframework.dao.DataIntegrityViolationException;
import ua.training.restaurant.dao.UserDao;
import ua.training.restaurant.dto.UserDto;
import ua.training.restaurant.entities.Role;
import ua.training.restaurant.entities.User;
import ua.training.restaurant.exceptions.NotUniqueUsernameException;
import ua.training.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(UserDto user) throws NotUniqueUsernameException {
        User u = User.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .role(Role.CUSTOMER)
                .build();
        try {
            return userDao.save(u);
        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueUsernameException();
        }
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
