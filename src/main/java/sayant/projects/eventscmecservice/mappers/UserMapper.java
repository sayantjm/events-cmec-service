package sayant.projects.eventscmecservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sayant.projects.eventscmecservice.domain.User;
import sayant.projects.eventscmecservice.model.UserDTO;

/**
 * Created by sayantjm on 19/12/20
 */
@Mapper(uses = DateMapper.class)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userTouserDTO(User user);

    User userDTOtoUser(UserDTO userDTO);
}
