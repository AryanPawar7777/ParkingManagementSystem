package com.parking.users.service;

import com.parking.users.dto.PaginatedResponse;
import com.parking.users.dto.UserDto;
import com.parking.users.repository.IUserRepository;
import com.parking.users.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class UserServiceImpl implements UserService {

    private static final int defaultPageSize = 10;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElse(null);
    }
   @Override
    public UserDto getUserByVehicleNumber(String vehicleNumber) {
        User user = userRepository.findByVehicleNumber(vehicleNumber);
        return (user != null) ? modelMapper.map(user, UserDto.class) : null;
    }

    @Override
    public void addUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> getUsersBySlotId(Long slotId) {
        return userRepository.findBySlotId(slotId)
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PaginatedResponse<UserDto> getPaginatedUsers(int page, Integer size) {
        int pageSize = (size != null && size > 0) ? size : defaultPageSize;

        Page<User> pagedUsers = userRepository.findAll(PageRequest.of(page, pageSize));

        List<UserDto> userDtos = pagedUsers.getContent()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());

        return new PaginatedResponse<>(
                pagedUsers.getNumber(),
                pagedUsers.getSize(),
                pagedUsers.getTotalPages(),
                pagedUsers.getTotalElements(),
                userDtos
        );
    }
}
