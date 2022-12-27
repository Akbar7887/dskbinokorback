package uz.dsk.binokorback.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.dsk.binokorback.fileupload.FileService;
import uz.dsk.binokorback.models.ImageData;
import uz.dsk.binokorback.sevice.ImageDataService;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.parseMediaType;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/imagedata/")
public class ImageDataResource {

    private final ImageDataService imageDataService;
    final FileService fileService;

    @PostMapping("save")
    private ResponseEntity<ImageData> save(@RequestBody ImageData imageData, @RequestParam("dom_id") String dom_id) {
        return ResponseEntity.ok().body(imageDataService.save(imageData, dom_id));
    }

    @GetMapping("get")
    private ResponseEntity<List<ImageData>> getImageAll(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok().body(imageDataService.getAllImage(id));
    }

    @GetMapping("getlayout")
    private ResponseEntity<List<ImageData>> getImagelayout(@RequestParam(value = "id") String id) {
        return ResponseEntity.ok().body(imageDataService.getAllImageLayout(id));
    }

    @PutMapping("webimage")
    private ResponseEntity<ImageData> webImage(@RequestParam("web") String web, @RequestParam("id") String id) {
        return ResponseEntity.ok().body(imageDataService.onweb(Boolean.parseBoolean(web), id));
    }

    @PutMapping("layotimage")
    private ResponseEntity<ImageData> layoutImage(@RequestParam("layout") String layout, @RequestParam("id") String id) {
        return ResponseEntity.ok().body(imageDataService.onlayout(Boolean.parseBoolean(layout), id));
    }

    @GetMapping("getbykompleks")
    private ResponseEntity<List<ImageData>> getAllByKompleks(@RequestParam("id") String id) {
        return ResponseEntity.ok().body(imageDataService.findAllByKompleks(id));
    }

    @DeleteMapping("remove")
    private void remove(@RequestParam("id") String id) throws IOException {
        imageDataService.remove(id);
    }

    @PostMapping(value = "upload")
    public ResponseEntity<?> uploadAndDownload(
            @RequestParam("id") String id,
            @RequestParam("file") MultipartFile file) {
        try {

            ImageData imageData = imageDataService.getById(Long.parseLong(id));
            String filetype = file.getOriginalFilename();
            imageData.setImagepath(imageData.getId() + "." + filetype.substring(filetype.lastIndexOf(".") + 1));
            imageDataService.save(imageData, imageData.getDom().getId().toString());
            return ResponseEntity.ok(fileService.storeFile(file, imageData.getImagepath(), "images"));

        } catch (Exception e) {
            return ResponseEntity.ok("Error while processing file");
        }
    }


    @GetMapping("download/images/{filename:.+}")
    public ResponseEntity<?> downloadFile(@PathVariable("filename") String filename,
                                          HttpServletRequest request) throws IOException {

//        Map map = fileService.DownloadFile(filename, "images");
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .headers((HttpHeaders) map.get("headers"))
//                .body(map.get("resource"));


        Resource fileResource = fileService.getFile(filename, "images");

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


}
