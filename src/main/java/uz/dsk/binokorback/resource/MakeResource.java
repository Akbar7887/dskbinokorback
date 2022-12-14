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
import uz.dsk.binokorback.models.Make;
import uz.dsk.binokorback.sevice.MakeService;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/make/")
public class MakeResource {

    @Autowired
    final MakeService makeService;
    final FileService fileService;


    @GetMapping("get")
    private ResponseEntity<List<Make>> getAll() {
        return ResponseEntity.ok().body(makeService.getAll());
    }

    @PostMapping("save")
    private ResponseEntity<Make> save(@RequestBody  Make make) {
        return ResponseEntity.ok().body(makeService.save(make));
    }

//    @PostMapping(value = "upload", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
//    public ResponseEntity<?> uploadAndDownload(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
//
//        Make make = makeService.getById(id);
//        String filetype = file.getOriginalFilename();
//        make.setImagepath(make.getId() +"."+ filetype.substring(filetype.lastIndexOf(".") + 1));
//        makeService.save(make);
//        try {
//            if (fileService.uploadAndDownloadFile(file, "makes", make.getImagepath())) {
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
//    @GetMapping("download/makes/{filename:.+}")
//    public ResponseEntity<?> downloadFile(@PathVariable("filename") String filename) throws IOException {
//
//        Map map = fileService.DownloadFile(filename, "makes");
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .headers((HttpHeaders) map.get("headers"))
//                .body(map.get("resource"));
//
//    }



}
