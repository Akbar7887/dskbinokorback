package uz.dsk.binokorback.sevice;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.dsk.binokorback.fileupload.FileService;
import uz.dsk.binokorback.models.Dom;
import uz.dsk.binokorback.models.ImageData;
import uz.dsk.binokorback.repo.DomRepo;
import uz.dsk.binokorback.repo.ImageDataRepo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ImageDataService {

    private final ImageDataRepo imageDataRepo;
    private final DomRepo domRepo;
    final FileService fileService;


    public ImageData save(ImageData imageData, String dom_id) {

        Optional<Dom> domOptional = domRepo.findById(Long.parseLong(dom_id));
        Dom dom = domOptional.orElse(null);
        imageData.setDom(dom);
        return imageDataRepo.save(imageData);
    }

    public List<ImageData> getAllImage(String id) {
        return imageDataRepo.findAllByIdDom(Long.parseLong(id), false);
    }

    public List<ImageData> getAllImageLayout(String id) {
        return imageDataRepo.findAllImageLayout(Long.parseLong(id), true);
    }

    public ImageData getById(Long id) {
        return imageDataRepo.getById(id);
    }

    public ImageData onweb(Boolean web, String id) {
        Optional<ImageData> imageDataOptional = imageDataRepo.findById(Long.parseLong(id));
        ImageData imageData = imageDataOptional.get();
        imageData.setWeb(web);
        return imageDataRepo.save(imageData);
    }

    public ImageData onlayout(Boolean layout, String id) {
        Optional<ImageData> imageDataOptional = imageDataRepo.findById(Long.parseLong(id));
        ImageData imageData = imageDataOptional.get();
        imageData.setLayout(layout);
        return imageDataRepo.save(imageData);
    }

    public List<ImageData> findAllByKompleks(String id) {
        return imageDataRepo.findAllByKompleks(Long.parseLong(id), true);
    }

    public void remove(String id) throws IOException {
        ImageData imageData = imageDataRepo.getById(Long.parseLong(id));
        Resource fileResource = fileService.getFile(imageData.getImagepath(), "images");

        File file = fileResource.getFile();
        file.delete();
        imageDataRepo.delete(imageData);
    }

}
