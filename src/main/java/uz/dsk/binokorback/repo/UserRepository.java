package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dsk.binokorback.models.auth.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
}
