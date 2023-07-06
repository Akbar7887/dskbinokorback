package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dsk.binokorback.models.Event;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {

    @Query("select 1, e from Event as e order by e.datecreate")
    Event getFirstById();
}
