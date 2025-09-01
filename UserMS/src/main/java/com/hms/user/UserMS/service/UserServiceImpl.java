package com.hms.user.UserMS.service;

import com.hms.user.UserMS.clients.ProfileClient;
import com.hms.user.UserMS.dto.Roles;
import com.hms.user.UserMS.dto.UserDto;
import com.hms.user.UserMS.entity.User;
import com.hms.user.UserMS.exception.HmsException;
import com.hms.user.UserMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //webclient
    @Autowired
    private ApiService apiService;

    @Autowired
    private ProfileClient profileClient;

    @Override
    public void registerUser(UserDto userDto) throws HmsException {
        Optional<User> opt= userRepository.findByEmail(userDto.getEmail());
        if(opt.isPresent()){
            throw new HmsException("USER_ALREADY_EXISTS");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        //Long profileId=apiService.addProfile(userDto).block();
        Long profileId=null;
        if(userDto.getRole().equals(Roles.DOCTOR)){
            profileId=profileClient.addDoctor(userDto);
        }else if(userDto.getRole().equals(Roles.PATIENT)){
            profileId=profileClient.addPatient(userDto);
        }
        System.out.println(profileId);
        userDto.setProfileId(profileId);
        userRepository.save(userDto.toEntity());
    }

    @Override
    public UserDto loginUser(UserDto userDto) throws HmsException {
        User user= userRepository.findByEmail(userDto.getEmail()).orElseThrow(()->new HmsException("USER_NOT_FOUND"));
        if(!passwordEncoder.matches(userDto.getPassword(),user.getPassword())){
            throw new HmsException("INVALID_CREDENTIALS");
        }
        user.setPassword(null);
        return user.toDto();
    }

    @Override
    public UserDto getUserById(Long id) throws HmsException {
        return userRepository.findById(id).orElseThrow(()->new HmsException("USER_NOT_FOUND")).toDto();
    }

    @Override
    public void updateUser(UserDto userDto) {

    }

    @Override
    public UserDto getUser(String email) throws HmsException {
        return userRepository.findByEmail(email).orElseThrow(()->new HmsException("USER_NOT_FOUND")).toDto();
    }
}
