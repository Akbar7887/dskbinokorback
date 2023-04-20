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
import java.util.Arrays;
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

    @GetMapping("v1/get")
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
                                                     @RequestParam("file") MultipartFile[] files) {

        Kompleks kompleks = kompleksService.getById(id);
        Arrays.asList(files).stream().forEach(file -> {
            int i = Arrays.asList(files).indexOf(file);
            String filetype = file.getOriginalFilename();
            String path = "";
            if (i == 0) {
                assert filetype != null;
                kompleks.setMainimagepath(kompleks.getId() + "-1." + filetype.substring(filetype.lastIndexOf(".") + 1));
                path = kompleks.getMainimagepath();
            } else if (i == 1) {
                assert filetype != null;
                kompleks.setMainimagepathfirst(kompleks.getId() + "-2." + filetype.substring(filetype.lastIndexOf(".") + 1));
                path = kompleks.getMainimagepathfirst();
            } else if (i == 2) {
                assert filetype != null;
                kompleks.setMainimagepathsecond(kompleks.getId() + "-3." + filetype.substring(filetype.lastIndexOf(".") + 1));
                path = kompleks.getMainimagepathsecond();
            }

            kompleksService.save(kompleks);
            ResponseEntity.ok(fileService.storeFile(file, path, "house"));

        });

        return ResponseEntity.ok().body(true);
    }


    @GetMapping("v1/download/house/{filename:.+}")
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

    @PutMapping(value = "removeimage")
    public Boolean removeImage(@RequestParam("id") String id, @RequestParam("filename") String filename) {
        boolean result = fileService.delete("house-" + filename);
        if (result) {
            Kompleks kompleks = kompleksService.getById(id);
            if (kompleks.getMainimagepath().equals(filename)) {
                kompleks.setMainimagepath("");
            } else if (kompleks.getMainimagepathfirst().equals(filename)) {
                kompleks.setMainimagepathfirst("");
            } else if (kompleks.getMainimagepathsecond().equals(filename)) {
                kompleks.setMainimagepathsecond("");
            }
            kompleksService.save(kompleks);
        }
        return result;
    }


}
