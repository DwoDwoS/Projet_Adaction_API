package adaction.controllers;

import adaction.models.Coordinate;
import adaction.services.CoordinatesService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/coordinates")
@CrossOrigin(origins = "${cors.allowed.origins}")
public class CoordinatesController {

    private final CoordinatesService coordinatesService;

    public CoordinatesController(CoordinatesService coordinatesService) {
        this.coordinatesService = coordinatesService;
    }

    // GET all
    @GetMapping
    public List<Coordinate> getAllCoordinates() {
        return coordinatesService.getAllCoordinates();
    }

    // POST create
    @PostMapping
    public ResponseEntity<Coordinate> createCoordinate(@RequestBody Coordinate coordinate) {
        Coordinate coordinateCreated = coordinatesService.createCoordinate(coordinate);
        return new ResponseEntity<>(coordinateCreated, HttpStatus.CREATED);
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Coordinate> getCoordinateById(@PathVariable String id) {
        Optional<Coordinate> coordinate = coordinatesService.getCoordinateById(id);

        if (coordinate.isPresent()) {
            return new ResponseEntity<>(coordinate.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // PUT update
    @PutMapping("/{id}")
    public ResponseEntity<Coordinate> updateCoordinate(@PathVariable String id, @RequestBody Coordinate coordinateDetails) {
        Optional<Coordinate> updatedCoordinate = coordinatesService.updateCoordinate(id, coordinateDetails);

        if (updatedCoordinate.isPresent()) {
            return new ResponseEntity<>(updatedCoordinate.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoordinate(@PathVariable String id) {
        boolean deleted = coordinatesService.deleteCoordinate(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

