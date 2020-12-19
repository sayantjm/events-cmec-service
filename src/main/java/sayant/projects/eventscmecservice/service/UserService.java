package sayant.projects.eventscmecservice.service;

import sayant.projects.eventscmecservice.model.UserDTO;

import java.util.List;

/**
 * Created by sayantjm on 19/12/20
 */
public interface UserService {

    UserDTO getByUserName(String username);

    UserDTO saveNewUser(UserDTO user);

    UserDTO updateUser(String username, UserDTO userDTO);

    List<UserDTO> listAll();

    List<UserDTO> listAllBySurname(String surname);

}
