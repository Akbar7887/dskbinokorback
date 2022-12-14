package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.dsk.binokorback.models.Active;
import uz.dsk.binokorback.models.Meneger;

import java.util.List;

@Repository
public interface MenegerRepo extends JpaRepository<Meneger, Long> {

    @Query("select m from Meneger m where m.active =:active")
    List<Meneger> getAllActive(@Param("active") Active active);

}
