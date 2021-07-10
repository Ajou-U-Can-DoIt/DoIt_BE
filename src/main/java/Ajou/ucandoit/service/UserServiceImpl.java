package Ajou.ucandoit.service;

import Ajou.ucandoit.domain.User;
import Ajou.ucandoit.dto.UserSaveRequestDto;
import Ajou.ucandoit.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

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
    public int isVaildUserName(String userName) {
        User vaildUserName = userRepository.isVaildUserName(userName);

        if(vaildUserName == null) return 0;
        else return -1;
    }

    @Override
    public String hashPwd(String pwd) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(pwd, salt);
    }

}
