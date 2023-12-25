package uz.dsk.binokorback.sevice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.dsk.binokorback.models.Active;
import uz.dsk.binokorback.models.Catalog;
import uz.dsk.binokorback.models.ImageCatalog;
import uz.dsk.binokorback.repo.CatalogRepo;
import uz.dsk.binokorback.repo.ImageCatalogRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CatalogService {

    //    @Autowired
    final CatalogRepo catalogRepo;
    final ImageCatalogRepo imageCatalogRepo;

    public List<Catalog> getallActive() {
        return catalogRepo.getAllActive(Active.ACTIVE);
    }

    public Catalog post(Catalog catalog) {
        return catalogRepo.save(catalog);
    }

    public Catalog postimage(String id, ImageCatalog imageCatalog) {

        Optional<Catalog> catalogOptional = catalogRepo.findById(Long.parseLong(id));
        Catalog catalog = catalogOptional.orElse(null);
        catalog.addImage(imageCatalog);
        return catalogRepo.save(catalog);
    }

    public Catalog remove(String id) {
        Optional<Catalog> catalogOptional = catalogRepo.findById(Long.parseLong(id));

        Catalog catalog = catalogOptional.orElse(null);

        catalog.setActive(Active.NOACTIVE);
        return catalogRepo.save(catalog);
    }

}
