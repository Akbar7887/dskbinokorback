package uz.dsk.binokorback.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.dsk.binokorback.models.Event;
import uz.dsk.binokorback.sevice.EventService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event/")
public class EventResource {

    @Autowired
    private final EventService eventService;



    @GetMapping("v1/get")
    private List<Event> getAll(){
        return eventService.getall();
    }

    @PostMapping("save")
    private Event save(@RequestBody Event event){
        return  eventService.save(event);
    }

    @DeleteMapping("delete")
    private void delete(@RequestParam("id") String id){
        eventService.remove(Long.parseLong(id));
    }

    @GetMapping("v1/getone")
    private Event getLastOne(){
        return eventService.getLastOne();
    }
}
