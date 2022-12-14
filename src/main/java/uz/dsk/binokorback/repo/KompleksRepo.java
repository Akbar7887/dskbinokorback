package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.dsk.binokorback.models.Active;
import uz.dsk.binokorback.models.Kompleks;

import java.util.List;

@Repository
public interface KompleksRepo extends JpaRepository<Kompleks, Long> {

    @Query("select h from Kompleks h where h.active=:active")
    public List<Kompleks> getAllActive(@Param("active") Active active);
}
