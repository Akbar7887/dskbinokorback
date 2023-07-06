package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dsk.binokorback.models.Event;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {

    @Query(value = "select 1, e from Event e order by e.datecreate desc")
    List<Event> getFirstById();
}
