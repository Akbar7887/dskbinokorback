package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.dsk.binokorback.models.Active;
import uz.dsk.binokorback.models.Make;

import java.util.List;

@Repository
public interface MakeRepo extends JpaRepository<Make, Long> {

    @Query("select m from Make m where m.active =:active")
    List<Make> findAllByActive(@Param("active") Active active);
}
