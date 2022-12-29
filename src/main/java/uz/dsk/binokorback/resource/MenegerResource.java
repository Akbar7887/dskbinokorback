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
import uz.dsk.binokorback.models.Meneger;
import uz.dsk.binokorback.sevice.MenegerService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.parseMediaType;

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

    @PostMapping(value = "upload")
    public ResponseEntity<?> uploadAndDownload(@RequestParam("id") String id,
                                               @RequestParam("file") MultipartFile file) {

        Meneger meneger = menegerService.getById(id);
        String filetype = file.getOriginalFilename();
        meneger.setImagepath(meneger.getId() + "." + filetype.substring(filetype.lastIndexOf(".") + 1));
        menegerService.save(meneger);
        try {

            return ResponseEntity.ok(fileService.storeFile(file, meneger.getImagepath(), "meneger"));
        } catch (Exception e) {
            return ResponseEntity.ok("Error while processing file");
        }
    }


    @GetMapping("download/meneger/{filename:.+}")
    public ResponseEntity<?> downloadFile(@PathVariable("filename") String filename,
                                          HttpServletRequest request) throws IOException {

        Resource fileResource = fileService.getFile(filename, "meneger");

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


    @PutMapping("remove")
    private ResponseEntity<Meneger> remove(@RequestParam("id") String id) throws IOException {
        Meneger meneger = menegerService.getById(id);
        try {
            File file = fileService.getFile(meneger.getImagepath(), "meneger").getFile();
            file.delete();
        } catch (Exception e) {

        }

        return ResponseEntity.ok().body(menegerService.remove(meneger));
    }


}
