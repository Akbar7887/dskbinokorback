package uz.dsk.binokorback.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dsk.binokorback.models.jobs.Job;
import uz.dsk.binokorback.sevice.JobService;

import java.util.List;

@RestController
@RequestMapping("/job/")
@RequiredArgsConstructor
public class JobResource {

    @Autowired
    final JobService jobService;

    @GetMapping("get")
    private ResponseEntity<List<Job>> getAllActive() {
        return ResponseEntity.ok().body(jobService.getAllActive());
    }

    @PostMapping("save")
    private ResponseEntity<Job> save(@RequestBody Job job) {
        return ResponseEntity.ok().body(jobService.save(job));
    }

    @PutMapping("remove")
    private ResponseEntity<Job> remove(@RequestParam("id") String id) {
        return ResponseEntity.ok().body(jobService.remove(id));
    }

    @PutMapping("removeitem")
    private ResponseEntity<Job> removeItem(@RequestParam("id") String id) {
        return ResponseEntity.ok().body(jobService.removeItem(id));
    }
}
