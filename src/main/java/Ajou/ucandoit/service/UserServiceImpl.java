package Ajou.ucandoit.service;

import Ajou.ucandoit.dto.UserSaveRequestDto;
import Ajou.ucandoit.repository.UserRepository;
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
    public String signUp(UserSaveRequestDto userSaveRequestDto) {
        return userRepository.save(userSaveRequestDto.toEntity()).getNickName();
    }

}
