package adaction.controllers;

import adaction.models.Volunteer;
import adaction.services.VolunteersService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/volunteers")
@CrossOrigin(origins = "${cors.allowed.origins}")
public class VolunteersController {
    private final VolunteersService volunteersService;

    public VolunteersController(VolunteersService volunteersService) {
        this.volunteersService = volunteersService;
    }

    // GET all
    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        return volunteersService.getAllVolunteers();
    }

    // POST create
    @PostMapping
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer){
        Volunteer volunteerCreated = volunteersService.createVolunteer(volunteer);
        return new ResponseEntity<>(volunteerCreated, HttpStatus.CREATED);
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(@PathVariable String id){
        Optional<Volunteer> volunteer = volunteersService.getVolunteerById(id);

        if(volunteer.isPresent()){
            return new ResponseEntity<>(volunteer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // PUT update
    @PutMapping("/{id}")
    public ResponseEntity<Volunteer> updateVolunteer(@PathVariable String id, @RequestBody Volunteer volunteerDetails){
        Optional<Volunteer> updatedVolunteer = volunteersService.updateVolunteer(id, volunteerDetails);

        if(updatedVolunteer.isPresent()){
            return new ResponseEntity<>(updatedVolunteer.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable String id){
        boolean deleted = volunteersService.deleteVolunteer(id);

        if(deleted){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

