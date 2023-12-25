package uz.dsk.binokorback.sevice;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.dsk.binokorback.models.Active;
import uz.dsk.binokorback.models.jobs.ItemJob;
import uz.dsk.binokorback.models.jobs.Job;
import uz.dsk.binokorback.repo.JobItemRepo;
import uz.dsk.binokorback.repo.JobRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class JobService {

    @Autowired
    final JobRepo jobRepo;
    @Autowired
    final JobItemRepo jobItemRepo;


    public List<Job> getAllActive() {
        return jobRepo.getAllActive(Active.ACTIVE);
    }

    public Job save(Job job) {
        return jobRepo.save(job);
    }

    public Job remove(String id) {
        Optional<Job> jobOptional = jobRepo.findById(Long.parseLong(id));
        Job job = jobOptional.orElse(null);

        job.setActive(Active.NOACTIVE);
        return job;
    }

    public Job removeItem(String id) {

        Optional<ItemJob> itemJobOptional = jobItemRepo.findById(Long.parseLong(id));
        if (itemJobOptional.isPresent()) {
            ItemJob itemJob = itemJobOptional.get();
            Job job = itemJob.getJob();

            job.removeItem(itemJob);
            jobItemRepo.delete(itemJob);
            return jobRepo.save(job);

        } else {
            return null;
        }

    }
}
