package sayant.projects.eventscmecservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import sayant.projects.eventscmecservice.domain.User;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sayantjm on 19/12/20
 */
@SpringBootTest
public class UserDaoTest {

    @Autowired
    protected UserRepository repository;

    @Test
    void shouldFindUser() {
        Optional<User> perajua = repository.findById("perajua");
        assertThat(perajua.isPresent()).isTrue();
        assertThat(perajua.get().getName()).isEqualTo("Juanma");

        Optional<User> test_username = repository.findById("test_username");
        assertThat(test_username.isPresent()).isFalse();

        List<User> allUsers = this.repository.findAll();
        assertThat(allUsers).hasSize(3);
    }

    @Test
    @Transactional
    void shouldInsertUser() {
        List<User> userList = this.repository.findAll();
        int found = userList.size();

        User newUser = User.builder()
                .username("newuser")
                .name("Testuser")
                .surname("TestUserSurname")
                .version(0L)
                .build();
        this.repository.save(newUser);
        userList = this.repository.findAll();
        assertThat(userList.size()).isEqualTo(found + 1);
    }

    @Test
    @Transactional
    void shouldUpdateUser() {
        Optional<User> perajua = this.repository.findById("perajua");
        assertThat(perajua.isPresent()).isTrue();
        User user = perajua.get();
        String surname = user.getSurname();
        String newSurname = surname + "X";

        user.setSurname(newSurname);
        this.repository.save(user);

        user = this.repository.findById("perajua").get();
        assertThat(user.getSurname()).isEqualTo(newSurname);

    }

    @Test
    @Transactional
    void shouldRemoveUser() {
        List<User> userList = this.repository.findAll();
        int found = userList.size();

        this.repository.delete(userList.get(0));

        userList = this.repository.findAll();
        assertThat(userList.size()).isEqualTo(found - 1);
    }
}
