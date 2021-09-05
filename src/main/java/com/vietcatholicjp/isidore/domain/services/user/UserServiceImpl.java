package com.vietcatholicjp.isidore.domain.services.user;

import com.vietcatholicjp.isidore.domain.exceptions.EmailExistedException;
import com.vietcatholicjp.isidore.domain.models.entities.User;
import com.vietcatholicjp.isidore.domain.repositories.UserRepository;
import com.vietcatholicjp.isidore.domain.services.crypt.CryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CryptService cryptService;
    private final UserRepository userRepository;

    @Override
    public User signup(String email, String rawPwd) {
        if (userRepository.findByEmail(email) != null) {
            throw new EmailExistedException("this email is not available", null);
        }

        User user = new User(email, null);
        user.setHashedPwd(cryptService.hash(rawPwd));
        
        return userRepository.insert(user);
    }

    @Override
    public String login(String email, String rawPwd) {
        User user = userRepository.findByEmail(email);

        if (user == null || !cryptService.matches(rawPwd, user.getHashedPwd())) {
            return null;
        }

        return "OK";
    }
}
