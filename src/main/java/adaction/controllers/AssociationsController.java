package adaction.controllers;

import adaction.models.Association;
import adaction.models.Collect;
import adaction.services.AssociationsService;
import adaction.services.CollectsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/associations")
@CrossOrigin(origins = "${cors.allowed.origins}")
public class AssociationsController {

        private final AssociationsService associationsService;

        public AssociationsController(AssociationsService associationsService) {
            this.associationsService = associationsService;
        }

        @GetMapping
        public List<Association> getAllAssociation() {
            return associationsService.getAllAssociation();
        }


        @GetMapping("/{id}")
        public ResponseEntity<Association> getAssociationById(@PathVariable String id) {
            Optional<Association> association = associationsService.getAssociationById(id);

            if (association.isPresent()) {
                return new ResponseEntity<>(association.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteAssociation(@PathVariable String id) {
            boolean deleted = associationsService.deleteAssociation(id);

            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

}
