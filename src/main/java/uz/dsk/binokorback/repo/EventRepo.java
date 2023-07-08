package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.dsk.binokorback.models.Event;

import java.util.List;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {

    @Query(value = "SELECT e FROM Event e order by e.datecreate asc")
    List<Event> getFirstById();
}
