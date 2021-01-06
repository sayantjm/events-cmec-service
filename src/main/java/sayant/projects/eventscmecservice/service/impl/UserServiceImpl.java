package sayant.projects.eventscmecservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sayant.projects.eventscmecservice.domain.User;
import sayant.projects.eventscmecservice.exceptions.NotFoundException;
import sayant.projects.eventscmecservice.mappers.UserMapper;
import sayant.projects.eventscmecservice.model.UserDTO;
import sayant.projects.eventscmecservice.repository.UserRepository;
import sayant.projects.eventscmecservice.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by sayantjm on 19/12/20
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO getByUserName(String username) {
        return userMapper.userTouserDTO(userRepository.findById(username).orElseThrow(NotFoundException::new));
    }

    @Override
    public UserDTO saveNewUser(UserDTO userDTO) {
        return userMapper.userTouserDTO(userRepository.save(userMapper.userDTOtoUser(userDTO)));
    }

    @Override
    public UserDTO updateUser(String username, UserDTO userDTO) {
        User user = userRepository.findById(username).orElseThrow(NotFoundException::new);

        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());

        return userMapper.userTouserDTO(userRepository.save(user));
    }

    @Override
    public List<UserDTO> listAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream().map(userMapper::userTouserDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> listAllBySurname(String surname) {
        List<User> usersFound = userRepository.findBySurname(surname);
        return usersFound.stream().map(userMapper::userTouserDTO).collect(Collectors.toList());
    }

    @Override
    public void removeUser(String username) {
        User user = userRepository.findById(username).orElseThrow(NotFoundException::new);
        userRepository.delete(user);
    }
}
