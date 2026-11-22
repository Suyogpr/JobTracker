package com.stygian.jobtracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationRepository repo;

    public JobApplicationController(JobApplicationRepository repo) {
        this.repo = repo;
    }

@GetMapping     //READ all
public List<JobApplication> getAll(){
    return repo.findAll();
}

@GetMapping("/{id}")     //READ one
public ResponseEntity<JobApplication> getOne(@PathVariable Long id){
    return repo.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
}

@PostMapping    //CREATE
public JobApplication create(@RequestBody JobApplication app){
    return repo.save(app);
}

@PutMapping("/{id}")     //UPDATE
public ResponseEntity<JobApplication> update(@PathVariable Long id, @RequestBody JobApplication updated){
    return repo.findById(id)
            .map(existing -> {
                existing.setCompany(updated.getCompany());
                existing.setPosition(updated.getPosition());
                existing.setStatus(updated.getStatus());
                existing.setAppliedDate(updated.getAppliedDate());
                return ResponseEntity.ok(repo.save(existing));
            }).orElse(ResponseEntity.notFound().build());
}

@DeleteMapping("/{id}")  //DELETE
public ResponseEntity<Void> delete(@PathVariable Long id){
    if (!repo.existsById(id)) return ResponseEntity.notFound().build();
    repo.deleteById(id);
    return ResponseEntity.noContent().build();
}
}