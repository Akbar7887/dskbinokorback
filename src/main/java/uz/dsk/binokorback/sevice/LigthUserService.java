package uz.dsk.binokorback.sevice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.dsk.binokorback.models.LigthUser;
import uz.dsk.binokorback.repo.LigthUserRepo;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class LigthUserService {

    @Autowired
    final LigthUserRepo ligthUserRepo;

    public List<LigthUser> getAll() {
        return ligthUserRepo.findAll();
    }

    public LigthUser save(LigthUser ligthUser){
        return ligthUserRepo.save(ligthUser);
    }
}
