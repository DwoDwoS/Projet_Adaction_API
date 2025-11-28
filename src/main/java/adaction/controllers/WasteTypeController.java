package adaction.controllers;

import adaction.models.WasteType;
import adaction.services.WasteTypeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wastetype")
@CrossOrigin(origins = "${cors.allowed.origins}")
public class WasteTypeController {
    private final WasteTypeService wasteTypeService;

    public WasteTypeController(WasteTypeService wasteTypeService) {
        this.wasteTypeService = wasteTypeService;
    }

    // GET all
    @GetMapping
    public List<WasteType> getAllWasteType() {
        return wasteTypeService.getAllWasteType();
    }


    // GET by Label
    @GetMapping("/{id}")
    public ResponseEntity<String> getLabelById(@PathVariable String id) {
        Optional<WasteType> wasteType = wasteTypeService.getLabelById(id);

        if (wasteType.isPresent()) {
            return new ResponseEntity<>(wasteType.get().getLabel(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
