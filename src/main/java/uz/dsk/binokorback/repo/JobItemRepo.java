package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.dsk.binokorback.models.jobs.ItemJob;

public interface JobItemRepo extends JpaRepository<ItemJob, Long> {
}
