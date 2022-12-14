package uz.dsk.binokorback.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dsk.binokorback.models.Catalog;
import uz.dsk.binokorback.models.ImageCatalog;
import uz.dsk.binokorback.sevice.CatalogService;

import java.util.List;

@RequestMapping("/catalog/")
@RestController
@RequiredArgsConstructor
public class CatalogResource {

    @Autowired
    final CatalogService catalogService;

    @GetMapping("get")
    private ResponseEntity<List<Catalog>> getAllActive() {
        return ResponseEntity.ok().body(catalogService.getallActive());
    }

    @PostMapping("save")
    private ResponseEntity<Catalog> post(@RequestBody Catalog catalog) {
        return ResponseEntity.ok().body(catalogService.post(catalog));
    }

    @PostMapping("saveimage")
    private ResponseEntity<Catalog> postimage(@RequestParam("id") String id,
                                              @RequestBody ImageCatalog imageCatalog) {

        return  ResponseEntity.ok().body(catalogService.postimage(id, imageCatalog));
    }

    @PutMapping("remove")
    private ResponseEntity<Catalog> remove(@RequestParam("id") String id){
        return ResponseEntity.ok().body(catalogService.remove(id));
    }

}
