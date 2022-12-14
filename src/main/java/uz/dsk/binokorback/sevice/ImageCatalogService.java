package uz.dsk.binokorback.sevice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.dsk.binokorback.models.Catalog;
import uz.dsk.binokorback.models.ImageCatalog;
import uz.dsk.binokorback.repo.CatalogRepo;
import uz.dsk.binokorback.repo.ImageCatalogRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor

public class ImageCatalogService {

    @Autowired
    final ImageCatalogRepo imageCatalogRepo;
    final CatalogRepo catalogRepo;

    public List<ImageCatalog> getAll() {
        return imageCatalogRepo.findAll();
    }
    public List<ImageCatalog> getByCatalogId(String id) {

        return imageCatalogRepo.findByCatalog_Id(Long.parseLong(id));
    }

//    public ImageCatalog post(String id, ImageCatalog imageCatalog){
//        Optional<Catalog> catalogOptional = catalogRepo.findById(Long.parseLong(id));
//        if(catalogOptional.isPresent()){
//            Catalog catalog = catalogOptional.get();
//            catalog.addImage(imageCatalog);
//            catalogRepo.save(catalog);
//            return imageCatalogRepo.save(imageCatalog);
//        }else {
//            return null;
//        }
//    }

    public ImageCatalog postwithImage(String path, String id, ImageCatalog imageCatalog){
        Optional<Catalog> catalogOptional = catalogRepo.findById(Long.parseLong(id));
        Catalog catalog = catalogOptional.get();

        imageCatalog.setCatalog(catalog);
        imageCatalog.setImagepath(path);

        return imageCatalogRepo.save(imageCatalog);
    }

    public ImageCatalog getById(String id){
        Optional<ImageCatalog> imageCatalogOptional = imageCatalogRepo.findById(Long.parseLong(id));
        return imageCatalogOptional.get();
    }

}
