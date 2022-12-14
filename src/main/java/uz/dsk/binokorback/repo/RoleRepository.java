package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dsk.binokorback.models.auth.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByname(String name);
}
