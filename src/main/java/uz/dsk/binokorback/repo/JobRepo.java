package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.dsk.binokorback.models.Active;
import uz.dsk.binokorback.models.jobs.Job;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

    @Query("select j from Job j where j.active =:active")
    List<Job> getAllActive(@Param("active") Active active);
}
