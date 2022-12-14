package uz.dsk.binokorback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.dsk.binokorback.models.Active;
import uz.dsk.binokorback.models.Orderb;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orderb, Long> {

    @Query("select o from orderb o where o.active =:active")
    List<Orderb> getAll(@Param("active") Active active);
}
