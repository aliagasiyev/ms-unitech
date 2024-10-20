package az.edu.turing.msunitech.domain.repository;

import az.edu.turing.msunitech.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean checkByUsername(String username);

    Optional<UserEntity> findByUsername(String username);
}
