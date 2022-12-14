package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.dsk.binokorback.models.ImageData;

import java.util.List;

public interface ImageDataRepo extends JpaRepository<ImageData, Long> {

    @Query("select  i from image_data i where i.dom.id = :id and i.layout = :layout")
    List<ImageData> findAllByIdDom(@Param("id") Long id, @Param("layout") boolean layout);

    @Query("select i from image_data i where i.dom.kompleks.id = :id and i.web =:web")
    List<ImageData> findAllByKompleks(@Param("id") Long id, @Param("web") Boolean web);

    @Query("select  i from image_data i where i.dom.kompleks.id  = :id and i.layout = :layout")
    List<ImageData> findAllImageLayout(@Param("id") Long id, @Param("layout") boolean layout);



}
