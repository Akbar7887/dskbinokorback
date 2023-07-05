package uz.dsk.binokorback.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.dsk.binokorback.models.Event;
import uz.dsk.binokorback.repo.EventRepo;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private final EventRepo eventsRepo;


    public Event save(Event event){
        return  eventsRepo.save(event);
    }

    public List<Event> getall(){
        return eventsRepo.findAll();
    }

    public void remove(Long id){
        eventsRepo.deleteById(id);
    }


}
