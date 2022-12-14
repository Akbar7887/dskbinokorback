package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.dsk.binokorback.models.Make;

@Repository
public interface MakeRepo extends JpaRepository<Make, Long> {

}
