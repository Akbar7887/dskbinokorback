package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.dsk.binokorback.models.ImageNews;

@Repository
public interface ImageNewsRepository extends JpaRepository<ImageNews, Long> {
}