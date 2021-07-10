package Ajou.ucandoit.service;

import Ajou.ucandoit.domain.User;
import Ajou.ucandoit.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public String signUp(User user) {
        return userRepository.save(user).getNickName();
    }

    @Override
    public int isValidUserName(String userName) {
        User vaildUserName = userRepository.findByUserName(userName);

        if(vaildUserName == null) return 0;
        else return -1;
    }

    @Override
    public String hashPwd(String pwd) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(pwd, salt);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean verifyPwd(String pwd, String hashPwd) {
        return BCrypt.checkpw(pwd, hashPwd);
    }

}
