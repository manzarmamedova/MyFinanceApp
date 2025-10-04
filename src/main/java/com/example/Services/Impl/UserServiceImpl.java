package com.example.Services.Impl;

import com.example.dto.DtoUser;
import com.example.dto.DtoUserIU;
import com.example.repository.TransactionRepository;
import com.example.repository.UserRepository;
import com.example.Services.IUserService;
import com.example.entity.User;
import com.example.exception.InvalidRequestException;
import com.example.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of IUserService for managing users.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public DtoUser createUser(DtoUserIU dtoUserIU) {
        // Validation
        if (dtoUserIU.getUserName() == null || dtoUserIU.getUserName().isBlank()) {
            throw new InvalidRequestException("User name cannot be empty");
        }
        if (dtoUserIU.getEmail() == null || dtoUserIU.getEmail().isBlank()) {
            throw new InvalidRequestException("User email cannot be empty");
        }

        User user = new User();
        BeanUtils.copyProperties(dtoUserIU, user);

        userRepository.save(user);

        DtoUser responseDto = new DtoUser();
        BeanUtils.copyProperties(user, responseDto);
        return responseDto;
    }

    @Override
    public List<DtoUser> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    DtoUser dto = new DtoUser();
                    BeanUtils.copyProperties(user, dto);
                    return dto;
                })
                .toList();
    }

    @Override
    public DtoUser updateUser(Long id, DtoUserIU dtoUserIU) {
        // Throw exception if user not found
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (dtoUserIU.getUserName() != null && !dtoUserIU.getUserName().isBlank()) {
            user.setUserName(dtoUserIU.getUserName());
        }
        if (dtoUserIU.getEmail() != null && !dtoUserIU.getEmail().isBlank()) {
            user.setEmail(dtoUserIU.getEmail());
        }
        if (dtoUserIU.getRole() != null) {
            user.setRole(dtoUserIU.getRole());
        }

        userRepository.save(user);

        DtoUser responseDto = new DtoUser();
        BeanUtils.copyProperties(user, responseDto);
        return responseDto;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        // Throw exception if user not found
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        // Delete all transactions associated with the user
        transactionRepository.deleteAllByUserId(id);
        userRepository.deleteById(id);
    }

    @Override
    public DtoUser getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        DtoUser dto = new DtoUser();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }

}
