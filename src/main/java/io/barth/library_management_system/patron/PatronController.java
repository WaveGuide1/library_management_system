package io.barth.library_management_system.patron;

import io.barth.library_management_system.exception.EntityNotFoundException;
import io.barth.library_management_system.exception.InternalServerErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patrons")
public class PatronController {

    private final PatronServiceImp patronServiceImp;

    public PatronController(PatronServiceImp patronServiceImp) {
        this.patronServiceImp = patronServiceImp;
    }

    // Get all patrons
    @GetMapping
    public ResponseEntity<List<Patron>> getPatrons(){
        try {
            return new ResponseEntity<>(patronServiceImp.getAllPatron(), HttpStatus.OK);
        } catch (InternalServerErrorException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Save a patron
    @PostMapping
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron){
        try {
            Patron newPatron = patronServiceImp.createPatron(patron);
            return new ResponseEntity<>(newPatron, HttpStatus.CREATED);
        } catch (InternalServerErrorException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update patron info
    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(
            @PathVariable Long id, @RequestBody Patron patron
    ){
        try {
            Patron updatedPatron = patronServiceImp.updatePatron(id, patron);
            return new ResponseEntity<>(updatedPatron, HttpStatus.CREATED);
        } catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get patron by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {

        try {
            Patron patron = patronServiceImp.getPatronById(id);
            return new ResponseEntity<>(patron, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InternalServerErrorException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }

    }

    // Delete a patron
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id){
        try {
            patronServiceImp.deletePatron(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
