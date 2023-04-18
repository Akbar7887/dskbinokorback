package uz.dsk.binokorback.resource;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.dsk.binokorback.fileupload.FileService;
import uz.dsk.binokorback.models.ImageNews;
import uz.dsk.binokorback.models.Kompleks;
import uz.dsk.binokorback.models.News;
import uz.dsk.binokorback.sevice.ImageNewsService;
import uz.dsk.binokorback.sevice.NewsService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.springframework.http.MediaType.parseMediaType;

@Log4j2
@RequestMapping("/news/")
@RestController
@RequiredArgsConstructor
public class NewsResource {
    @Autowired
    final NewsService newsService;
    @Autowired
    final FileService fileService;
    @Autowired
    final ImageNewsService imageNewsService;


    @GetMapping("get")
    private ResponseEntity<List<News>> getAll() {
        return ResponseEntity.ok().body(newsService.getAllActive());
    }

    @PostMapping("save")
    private ResponseEntity<News> save(@RequestBody News news) {
        return ResponseEntity.ok().body(newsService.save(news));
    }

    @PutMapping("remove")
    private ResponseEntity<News> remove(@RequestParam("id") String id) {
        return ResponseEntity.ok().body(newsService.remote(id));
    }

    @PostMapping(value = "upload")
    public ResponseEntity<?> uploadAndDownload(@RequestParam("id") String id,
                                               @RequestParam("file") MultipartFile file) {

        News news = newsService.getById(Long.parseLong(id));
        String filetype = file.getOriginalFilename();
        news.setImagepath(news.getId() + "." + filetype.substring(filetype.lastIndexOf(".") + 1));
        newsService.save(news);
        try {

            return ResponseEntity.ok(fileService.storeFile(file, news.getImagepath(), "news"));
        } catch (Exception e) {
            return ResponseEntity.ok("Error while processing file");
        }
    }


    @GetMapping("download/news/{filename:.+}")
    public ResponseEntity<?> downloadFile(@PathVariable("filename") String filename,
                                          HttpServletRequest request) throws IOException {

        Resource fileResource = fileService.getFile(filename, "news");

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(fileResource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.error("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFilename() + "\"")
                .body(fileResource);


    }


    @GetMapping("download/imagenews/{filename:.+}")
    public ResponseEntity<?> imagenewsdownloadFile(@PathVariable("filename") String filename, HttpServletRequest request) {

        Resource fileResource = fileService.getFile(filename, "Imagenews");

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(fileResource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.error("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFilename() + "\"")
                .body(fileResource);

    }


    @PostMapping(value = "imagenewsupload")
    public ResponseEntity<?> imagenewsuploadFile(
            @RequestParam(value = "id") String id,
            @RequestParam("file") MultipartFile[] files) throws IOException {
        News news = newsService.getById(Long.parseLong(id));
        Arrays.asList(files).forEach(file -> {
            int i = Arrays.asList(files).indexOf(file);
            String filetype = file.getOriginalFilename();
            String path = "";
            ImageNews imageNews = new ImageNews();
            assert filetype != null;
            imageNews.setImagepath(news.getImageNewsList().size()+1 + "-"+news.getId() +"."+ filetype.substring(filetype.lastIndexOf(".") + 1));
            path = news.getImageNewsList().size()+1 + "-"+ news.getId() +"."+ filetype.substring(filetype.lastIndexOf(".") + 1);
            news.addImage(imageNews);
            newsService.save(news);
            ResponseEntity.ok(fileService.storeFile(file, path, "Imagenews"));
        });

        return ResponseEntity.ok().body(true);
    }

    @PostMapping(value = "videoupload")
    public ResponseEntity<?> videouploadFile(
            @RequestParam(value = "id") String id,
            @RequestParam("file") MultipartFile file) throws IOException {

        News news = newsService.getById(Long.parseLong(id));

        String filename = fileService.getType(file);
        news.setVideopath(news.getId() + filename);
        newsService.save(news);

        try {

            return ResponseEntity.ok(fileService.storeFile(file, news.getVideopath(), "videonews"));
        } catch (Exception e) {
            return ResponseEntity.ok("Error while processing file");
        }
    }
    @GetMapping("download/newsvideo/{filename:.+}")
    public ResponseEntity<?> downloadVideoFile(@PathVariable("filename") String filename,
                                          HttpServletRequest request) throws IOException {

        Resource fileResource = fileService.getFile(filename, "videonews");

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(fileResource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.error("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResource.getFilename() + "\"")
                .body(fileResource);
    }

    @PutMapping(value = "removenewsimage")
    public Boolean removeImage(@RequestParam("id") String id) {

            News news = newsService.getById(Long.parseLong(id));

            boolean result = fileService.delete("news" + "-" + news.getImagepath());
            if (result) {
                news.setImagepath(null);
                newsService.save(news);
            }

        return result;
    }

    @DeleteMapping(value = "removeimagenews")
    public Boolean removeImageNews(@RequestParam("id") String id) {

        ImageNews imageNews = imageNewsService.getById(Long.parseLong(id));

        boolean result = fileService.delete("Imagenews" + "-" + imageNews.getImagepath());
        if (result) {
            imageNews.setImagepath(null);
            imageNewsService.deleteById(Long.parseLong(id));
        }

        return result;
    }


}
