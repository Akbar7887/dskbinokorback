package uz.dsk.binokorback.fileupload;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;


@Log4j2
@Service
public class FileService {

    private final Path fileStorageLocation;

    @Autowired
    public FileService(FileStorageProperties fileStorageProperties) {
        fileStorageLocation = Path.of(fileStorageProperties.getUploadDir());
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (IOException e) {
            log.error("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    public String storeFile(MultipartFile multipartFile, String fotofilename, String folder) {

        String filename = StringUtils.cleanPath(Objects.requireNonNull(fotofilename));
        log.info(filename);

        try {
            filename = filename.substring(0, filename.lastIndexOf("."))
                    .replace(".", "") + "." + filename
                    .substring(filename.lastIndexOf(".") + 1);
            Path targetLocation = fileStorageLocation.resolve(folder +"/"+filename); //
//            Files.write(targetLocation, bytes);
            Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return targetLocation.toString();
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException("Could not store file" + filename, e);
        }
    }

    public Resource getFile(String filename, String folder) {
        try {
            Path file = fileStorageLocation.resolve(folder + "/"+filename).normalize();
            Resource resource = new UrlResource((file.toUri()));
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }

        } catch (IOException e) {
            throw new RuntimeException("Could not retrieve file " + filename, e);
        }
    }


}