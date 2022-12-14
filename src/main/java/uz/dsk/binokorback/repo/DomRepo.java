package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.dsk.binokorback.models.Active;
import uz.dsk.binokorback.models.Dom;

import java.util.List;

public interface DomRepo extends JpaRepository<Dom, Long> {

    @Query("select d from dom d where d.kompleks.id = :id and d.active = :act")
    List<Dom> findAllByKompleksAndId(Long id, Active act);
}
