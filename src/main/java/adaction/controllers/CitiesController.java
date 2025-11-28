package adaction.controllers;

import adaction.models.City;
import adaction.models.Volunteer;
import adaction.services.CitiesService;

import adaction.services.VolunteersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin(origins = "${cors.allowed.origins}")
public class CitiesController {

    private final CitiesService citiesService;

    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }
    // GET all
    @GetMapping
    public List<City> getAllCities() {
        return citiesService.getAllCities();
    }

    // POST create
    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city){
        City cityCreated = citiesService.createCity(city);
        return new ResponseEntity<>(cityCreated, HttpStatus.CREATED);
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id){
        Optional<City> city = citiesService.getCityById(id);

        if(city.isPresent()){
            return new ResponseEntity<>(city.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // PUT update
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City cityDetails){
        Optional<City> updatedCity = citiesService.updateCity(id, cityDetails);

        if(updatedCity.isPresent()){
            return new ResponseEntity<>(updatedCity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id){
        boolean deleted = citiesService.deleteCity(id);

        if(deleted){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

