package uz.dsk.binokorback.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dsk.binokorback.models.Dom;
import uz.dsk.binokorback.sevice.DomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dom/")
public class DomResource {

    private final DomService domService;

    @GetMapping("get")
    private List<Dom> getAllDobByIdKompleks(@RequestParam(value = "id") String id){
        return domService.getDomByIdKompleks(id);
    }

    @PostMapping("save")
    private ResponseEntity<Dom> postDom(@RequestBody  Dom dom, @RequestParam(value = "id") String id){
        return ResponseEntity.ok().body(domService.saveDom(dom, id));
    }

    @PostMapping("remove")
    private ResponseEntity<Dom> deleteDom(@RequestParam("id") String id){
        return ResponseEntity.ok().body(domService.deleteDom(id));
    }
}
