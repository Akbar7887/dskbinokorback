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
import uz.dsk.binokorback.models.Dom;
import uz.dsk.binokorback.models.Kompleks;
import uz.dsk.binokorback.sevice.KompleksService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.parseMediaType;

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


    @PostMapping(value = "upload")
    public ResponseEntity<Boolean> uploadAndDownload(@RequestParam("id") String id,
                                                     @RequestParam("file") MultipartFile file,
                                                     @RequestParam("file0") MultipartFile file0,
                                                     @RequestParam("file1") MultipartFile file1) {

        log.info(id);

        String filetype = "";
        String filetype0 = "";
        String filetype1 = "";
        Kompleks kompleks = kompleksService.getById(id);
        if (file != null) {
            filetype = file.getOriginalFilename();
            kompleks.setMainimagepath(kompleks.getId() + "-1." + filetype.substring(filetype.lastIndexOf(".") + 1));
        }
        if (file0 != null) {
            filetype0 = file0.getOriginalFilename();
            kompleks.setMainimagepathfirst(kompleks.getId() + "-2." + filetype0.substring(filetype0.lastIndexOf(".") + 1));
        }
        if (file1 != null) {
            filetype1 = file1.getOriginalFilename();
            kompleks.setMainimagepathsecond(kompleks.getId() + "-3." + filetype1.substring(filetype1.lastIndexOf(".") + 1));
        }

        kompleksService.save(kompleks);
        boolean ans = false;

        try {
            if (!filetype.isEmpty()) {
                ResponseEntity.ok(fileService.storeFile(file, kompleks.getMainimagepath(), "house"));
                ans = true;
            }
            if (!filetype0.isEmpty()) {
                ResponseEntity.ok(fileService.storeFile(file0, kompleks.getMainimagepathfirst(), "house"));
                ans = true;
            }

            if (!filetype1.isEmpty()) {
                ResponseEntity.ok(fileService.storeFile(file1, kompleks.getMainimagepathsecond(), "house"));
                ans = true;
            }
            if (ans) {
                kompleksService.save(kompleks);

                return ResponseEntity.ok().body(true);
            }

            return ResponseEntity.ok().body(false);
        } catch (Exception e) {
            return ResponseEntity.ok().body(false);
        }
    }


    @GetMapping("download/house/{filename:.+}")
    public ResponseEntity<?> downloadFile(@PathVariable("filename") String filename, HttpServletRequest request) throws IOException {

        Resource fileResource = fileService.getFile(filename, "house");


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

    @DeleteMapping(value = "removeimage")
    public Boolean removeImage(@RequestParam("filename") String filename) {
        return fileService.delete("house-" + filename);
    }


}
