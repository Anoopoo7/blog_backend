package lxiyas.example.backend.Users.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import lxiyas.example.backend.Users.Utils.UserExceptions;
import lxiyas.example.backend.Users.models.User;
import lxiyas.example.backend.Users.repository.UserRepository;

@Service
@Log4j2
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws Exception {
        log.info("81fcd6b5-12bd-4321-8da7-8d8d26d5bbda", "recieved product with details {}", user);
        if (null != user) {
            user.setCreatedDate(new Date());
            user.setActive(true);
            log.info("4bb8c0ca-01cc-40b6-9406-56b1d6ffecf5", "saving user with details {}", user);
            userRepository.save(user);
            return user;
        } else {
            log.error("8735f668-af55-4748-ab38-3fd086284f5c", "cannot save the user {} becasuse of insufficient input",
                    user);
            throw new Exception(UserExceptions.INVALID_INPUT.name());
        }
    }

    public Object getUserByEmailAndPassword(User user) throws Exception {
        if (null == user) {
            log.error("5263246a-7581-4c8b-a529-16a34af42d22", "cannot login the user {} becasuse of insufficient input",
                    user);
            throw new Exception(UserExceptions.INVALID_INPUT.name());
        } else {
            String email = user.getEmail();
            String password = user.getPassword();
            User currentUser = null;
            if (null != email && null != password) {
                currentUser = userRepository.findByEmailAndPassword(email, password);
                log.info("e17610c3-e095-4f96-ac19-472c1e5f7db0", "fetched user {}", user);
            }
            if (null != currentUser) {
                return currentUser;
            } else {
                throw new Exception(UserExceptions.USER_DO_NOT_EXISTS.name());
            }
        }
    }

}
