package claudiogiasi.eserciziou4w3d1.repositories;

import claudiogiasi.eserciziou4w3d1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
