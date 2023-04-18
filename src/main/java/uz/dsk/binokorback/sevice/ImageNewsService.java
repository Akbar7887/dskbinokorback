package uz.dsk.binokorback.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.dsk.binokorback.models.ImageNews;
import uz.dsk.binokorback.repo.ImageDataRepo;
import uz.dsk.binokorback.repo.ImageNewsRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageNewsService {

    @Autowired
    final ImageNewsRepository imageNewsRepository;

    public  ImageNews getById(Long id){
        return imageNewsRepository.findById(id).orElse(null);
    }
    public ImageNews save(ImageNews imageNews){
        return imageNewsRepository.save(imageNews);
    }
    public void deleteById(Long id ){
        imageNewsRepository.deleteById(id);
    }
}
