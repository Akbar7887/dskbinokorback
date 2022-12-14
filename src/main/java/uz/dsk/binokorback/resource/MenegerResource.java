package uz.dsk.binokorback.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.dsk.binokorback.fileupload.FileService;
import uz.dsk.binokorback.models.Meneger;
import uz.dsk.binokorback.sevice.MenegerService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/meneger/")
public class MenegerResource {

    final MenegerService menegerService;
    final FileService fileService;

    @GetMapping("get")
    private ResponseEntity<List<Meneger>> getAllActiver() {
        return ResponseEntity.ok().body(menegerService.getAllActive());
    }

    @PostMapping("save")
    private ResponseEntity<Meneger> save(@RequestBody Meneger meneger) {
        return ResponseEntity.ok().body(menegerService.save(meneger));
    }

//    @PostMapping(value = "upload", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
//    public ResponseEntity<?> uploadAndDownload(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
//
//        Meneger meneger = menegerService.getById(id);
//        String filetype = file.getOriginalFilename();
//        meneger.setImagepath(meneger.getId() +"."+ filetype.substring(filetype.lastIndexOf(".") + 1));
//        menegerService.save(meneger);
//        try {
//            if (fileService.uploadAndDownloadFile(file, "meneger", meneger.getImagepath())) {
//                final ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(fileService
//                        .getFileStorageLocation() + "/" + file.getOriginalFilename())));
//                return ResponseEntity.status(HttpStatus.OK).contentLength(resource.contentLength()).body(resource);
//            }
//            return ResponseEntity.ok("Error while processing file");
//        } catch (Exception e) {
//            return ResponseEntity.ok("Error while processing file");
//        }
//    }
//
//
//    @GetMapping("download/meneger/{filename:.+}")
//    public ResponseEntity<?> downloadFile(@PathVariable("filename") String filename) throws IOException {
//
//        Map map = fileService.DownloadFile(filename, "meneger");
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .headers((HttpHeaders) map.get("headers"))
//                .body(map.get("resource"));
//
//    }


    @PutMapping("remove")
    private ResponseEntity<Meneger> remove(@RequestParam("id") String id) throws IOException {
        Meneger meneger = menegerService.getById(id);
        try {
            File file = fileService.getFile(meneger.getImagepath(), "meneger").getFile();
            file.delete();
        }catch (Exception e){

        }

        return ResponseEntity.ok().body(menegerService.remove(meneger));
    }




}
