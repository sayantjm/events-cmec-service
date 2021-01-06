package sayant.projects.eventscmecservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sayant.projects.eventscmecservice.domain.User;
import sayant.projects.eventscmecservice.model.UserDTO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by sayantjm on 19/12/20
 */
@SpringBootTest
public class UserServiceTest {
    private final String surname = "UserTestSurname";

    @Autowired
    private UserService userService;

    @Test
    public void shouldFindUser() {
        UserDTO perajua = userService.getByUserName("perajua");
        assertThat(perajua).isNotNull();
        assertThat(perajua.getName()).isEqualTo("Juanma");
        assertThat(perajua.getSurname()).isEqualTo("Perales");
    }

    @Test
    public void shouldNotFindUserAndThrowException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            UserDTO perajua = userService.getByUserName("notfound");
        });
    }

    @Test
    public void shouldFindAllUsers() {
        List<UserDTO> listUsers = userService.listAll();
        assertThat(listUsers).hasSize(3);
    }

    @Test
    public void shouldCreateUser() {
        UserDTO newUser = UserDTO.builder()
                .username("usertest")
                .name("UserTestName")
                .surname(surname)
                .build();

        UserDTO savedUser = userService.saveNewUser(newUser);

        assertThat(newUser.getUsername()).isEqualTo(savedUser.getUsername());
        assertThat(newUser.getName()).isEqualTo(savedUser.getName());
        assertThat(newUser.getSurname()).isEqualTo(savedUser.getSurname());
    }

    @Test
    public void shouldUpdateUser() {

        UserDTO existingUser = userService.getByUserName("perajua");
        existingUser.setSurname(surname);

        userService.updateUser("perajua", existingUser);

        UserDTO updatedUser = userService.getByUserName("perajua");

        assertThat(updatedUser.getSurname()).isEqualTo(surname);
    }

    @Test
    public void shouldFindBySurname() {

        List<UserDTO> listUsers = userService.listAllBySurname("Perales");

        assertThat(listUsers).hasSize(1);

        // Adding new user with the same Surname
        UserDTO newUser = UserDTO.builder()
                .username("usertest")
                .name("UserTestName")
                .surname("Perales")
                .build();

        UserDTO savedUser = userService.saveNewUser(newUser);

        listUsers = userService.listAllBySurname("Perales");
        assertThat(listUsers).hasSize(2);
    }

    @Test
    public void shouldRemoveUser() {

        UserDTO existingUser = userService.getByUserName("perajua");
        assertThat(existingUser).isNotNull();

        userService.removeUser("perajua");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            UserDTO perajua = userService.getByUserName("perajua");
        });
    }
}
