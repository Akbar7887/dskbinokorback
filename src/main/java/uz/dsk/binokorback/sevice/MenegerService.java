package uz.dsk.binokorback.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.dsk.binokorback.models.Active;
import uz.dsk.binokorback.models.Meneger;
import uz.dsk.binokorback.repo.MenegerRepo;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MenegerService {

    @Autowired
    private final MenegerRepo menegerRepo;

    public List<Meneger> getAllActive() {
        return menegerRepo.getAllActive(Active.ACTIVE);
    }

    public Meneger save(Meneger meneger) {
        return menegerRepo.save(meneger);
    }

    public Meneger remove(Meneger meneger) {
        meneger.setActive(Active.NOACTIVE);
        return menegerRepo.save(meneger);
    }

    public Meneger getById(String id) {

        Optional<Meneger> menegerOptional = menegerRepo.findById(Long.parseLong(id));
        Meneger meneger = null;
        if (menegerOptional.isPresent()) {
            meneger = menegerOptional.get();
        }
        return meneger;
    }

}
