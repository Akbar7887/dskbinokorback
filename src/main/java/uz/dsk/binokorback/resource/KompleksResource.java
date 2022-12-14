package uz.dsk.binokorback.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dsk.binokorback.fileupload.FileService;
import uz.dsk.binokorback.models.Dom;
import uz.dsk.binokorback.models.Kompleks;
import uz.dsk.binokorback.sevice.KompleksService;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/kompleks/")
@RequiredArgsConstructor
public class KompleksResource {

    @Autowired
    final KompleksService kompleksService;
    @Autowired
    final FileService fileService;
    //    private BlobContainerClient blobContainerClient;
//    private BlobContainerClient blobContainerClient;

    @GetMapping("get")
    private ResponseEntity<List<Kompleks>> getAllActive() {
        return ResponseEntity.ok().body(kompleksService.getAllActive());
    }

    @PostMapping("save")
    private ResponseEntity<Kompleks> save(@RequestBody Kompleks kompleks) {
        return ResponseEntity.ok().body(kompleksService.save(kompleks));
    }

    @PutMapping("remove")
    private ResponseEntity<Kompleks> remove(@RequestParam("id") String id) {
        return ResponseEntity.ok().body(kompleksService.remove(id));
    }

    @PutMapping("removedom")
    private ResponseEntity<Dom> removedom(@RequestParam("id") String id) {
        return ResponseEntity.ok().body(kompleksService.removedom(id));
    }


//    @PostMapping(value = "upload", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
//    public ResponseEntity<Boolean> uploadAndDownload(@RequestParam("id") String id,
//                                                     @RequestParam("file") MultipartFile file,
//                                                     @RequestParam("file0") MultipartFile file0,
//                                                     @RequestParam("file1") MultipartFile file1) {
//
//
//        Kompleks kompleks = kompleksService.getById(id);
//        String filetype = file.getOriginalFilename();
//        String filetype0 = file0.getOriginalFilename();
//        String filetype1 = file1.getOriginalFilename();
//
//        boolean ans = false;
//
//        try {
//            if (!file.isEmpty()) {
//                kompleks.setMainimagepath(kompleks.getId() + "." + filetype.substring(filetype.lastIndexOf(".") + 1));
//                if (fileService.uploadAndDownloadFile(file, "house", kompleks.getMainimagepath())) {
//                    ans = true;
////                final ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(fileService
////                        .getFileStorageLocation() + "/" + file.getOriginalFilename())));
//                }
//            }
//            if (!file0.isEmpty()) {
//                kompleks.setMainimagepathfirst(kompleks.getId() + "-1." + filetype0.substring(filetype.lastIndexOf(".") + 1));
//                if (fileService.uploadAndDownloadFile(file0, "house", kompleks.getMainimagepathfirst())) {
//                    ans = true;
//
////                final ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(fileService
////                        .getFileStorageLocation() + "/" + file.getOriginalFilename())));
//                }
//            }
//            if (!file1.isEmpty()) {
//                kompleks.setMainimagepathsecond(kompleks.getId() + "-2." + filetype1.substring(filetype.lastIndexOf(".") + 1));
//                if (fileService.uploadAndDownloadFile(file1, "house", kompleks.getMainimagepathsecond())) {
//                    ans = true;
////                final ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(fileService
////                        .getFileStorageLocation() + "/" + file.getOriginalFilename())));
//                }
//            }
//            if (ans) {
//                kompleksService.save(kompleks);
//
//                return ResponseEntity.ok().body(true);
//            }
//
//            return ResponseEntity.ok().body(false);
//        } catch (Exception e) {
//            return ResponseEntity.ok().body(false);
//        }
//    }
//
//
//    @GetMapping("download/house/{filename:.+}")
//    public ResponseEntity<?> downloadFile(@PathVariable("filename") String filename) throws IOException {
//
//        Map map = fileService.DownloadFile(filename, "house");
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .headers((HttpHeaders) map.get("headers"))
//                .body(map.get("resource"));
//
//    }


}
