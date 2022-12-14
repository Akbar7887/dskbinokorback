package uz.dsk.binokorback.resource;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.dsk.binokorback.fileupload.FileService;
import uz.dsk.binokorback.models.ImageNews;
import uz.dsk.binokorback.models.News;
import uz.dsk.binokorback.sevice.NewsService;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Log4j2
@RequestMapping("/news/")
@RestController
@RequiredArgsConstructor
public class NewsResource {
    @Autowired
    final NewsService newsService;
    @Autowired
    final FileService fileService;


    @GetMapping("get")
    private ResponseEntity<List<News>> getAll() {
        return ResponseEntity.ok().body(newsService.getAllActive());
    }

    @PostMapping("save")
    private ResponseEntity<News> save(@RequestBody News news) {
        return ResponseEntity.ok().body(newsService.save(news));
    }

    @PutMapping("remove")
    private ResponseEntity<News> remove(@RequestParam("id") String id){
        return ResponseEntity.ok().body(newsService.remote(id));
    }

//    @PostMapping(value = "upload", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
//    public ResponseEntity<?> uploadAndDownload(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
//
//        News news = newsService.getById(id);
//        String filetype = file.getOriginalFilename();
//        news.setImagepath(news.getId() +"."+ filetype.substring(filetype.lastIndexOf(".") + 1));
//        newsService.save(news);
//        try {
//            if (fileService.uploadAndDownloadFile(file, "news", news.getImagepath())) {
//                final ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(fileService
//                        .getFileStorageLocation() + "/" + file.getOriginalFilename())));
//
//
//                return ResponseEntity.status(HttpStatus.OK).contentLength(resource.contentLength()).body(resource);
//            }
//            return ResponseEntity.ok("Error while processing file");
//        } catch (Exception e) {
//            return ResponseEntity.ok("Error while processing file");
//        }
//    }
//
//
//    @GetMapping("download/news/{filename:.+}")
//    public ResponseEntity<?> downloadFile(@PathVariable("filename") String filename) throws IOException {
//
//        Map map = fileService.DownloadFile(filename, "news");
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .headers((HttpHeaders) map.get("headers"))
//                .body(map.get("resource"));
//
//    }



//    @GetMapping("download/imagenews/{filename:.+}")
//    public ResponseEntity<?> imagenewsdownloadFile(@PathVariable("filename") String filename, HttpServletRequest request) {
//        Map map = fileService.DownloadFile(filename, "imagenews");
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .headers((HttpHeaders) map.get("headers"))
//                .body(map.get("resource"));
//
//    }


//    @PostMapping(value = "imagenewsupload",  produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
//    public ResponseEntity<?> imagenewsuploadFile(
//            @RequestParam(value = "id") String id,
//            @RequestParam("file") MultipartFile file) throws IOException {
//
//        News news = newsService.getById(id);
//        String filetype = file.getOriginalFilename();
//        Random random = new Random();
//        int min = 0;
//        int max = 10000;
//        int randomNumber = random.nextInt(max + 1 - min) + min;
//        String filename = String.valueOf(randomNumber) + "." + filetype.substring(filetype.lastIndexOf(".") + 1);
//
//        filename = filename.replace("'", "");
//
//        try {
//            if (fileService.uploadAndDownloadFile(file, "imagenews", filename)) {
//                final ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(fileService
//                        .getFileStorageLocation() + "/" + file.getOriginalFilename())));
//
//                ImageNews imageNews = new ImageNews();
//                imageNews.setImagepath(filename);
//                news.addImage(imageNews);
//                newsService.save(news);
//                return ResponseEntity.status(HttpStatus.OK).contentLength(resource.contentLength()).body(resource);
//            }
//            return ResponseEntity.ok("Error while processing file");
//        } catch (Exception e) {
//            return ResponseEntity.ok("Error while processing file");
//        }
//    }





}
