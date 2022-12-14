package uz.dsk.binokorback.sevice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.dsk.binokorback.models.Active;
import uz.dsk.binokorback.models.Orderb;
import uz.dsk.binokorback.repo.OrderRepo;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

    @Autowired
    final OrderRepo orderRepo;

   public List<Orderb> getallActive() {
        return orderRepo.getAll(Active.ACTIVE);
    }

    public Orderb save(Orderb orderb){
       return  orderRepo.save(orderb);
    }
}
