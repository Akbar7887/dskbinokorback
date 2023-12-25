package uz.dsk.binokorback.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        News news = optionalNews.orElse(null);
        news.setActive(Active.NOACTIVE);
        return newsRepo.save(news);
    }

    public News getById(Long id) {
        return newsRepo.findById(id).orElse(null);
    }


//    public News saveFile(MultipartFile file) {
//
//
//    }
}
