package uz.dsk.binokorback.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.dsk.binokorback.models.Active;
import uz.dsk.binokorback.models.News;
import uz.dsk.binokorback.repo.NewsRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NewsService {

    @Autowired
    final NewsRepo newsRepo;


    public List<News> getAllActive() {
        return newsRepo.getAllActive(Active.ACTIVE);
    }

    public News save(News news) {
        return newsRepo.save(news);
    }

    public News remote(String id) {

        Optional<News> optionalNews = newsRepo.findById(Long.parseLong(id));
        if (optionalNews.isPresent()) {
            News news = optionalNews.get();
            news.setActive(Active.NOACTIVE);
            return newsRepo.save(news);
        } else {
            return null;
        }
    }

    public News getById(String id) {
        Optional<News> newsOptional = newsRepo.findById(Long.parseLong(id));
        if (newsOptional.isPresent()) {
            News news = newsOptional.get();
            return news;
        } else {
            return null;
        }
    }


//    public News saveFile(MultipartFile file) {
//
//
//    }
}
