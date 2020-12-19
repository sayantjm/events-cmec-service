package sayant.projects.eventscmecservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import sayant.projects.eventscmecservice.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by sayantjm on 19/12/20
 */
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    @Override
    Optional<User> findById(String s);

    List<User> findAll();

    List<User> findBySurname(String surname);
}
