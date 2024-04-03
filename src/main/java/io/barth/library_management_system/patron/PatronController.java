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

    @GetMapping
    public ResponseEntity<List<Patron>> getPatrons(){
        return new ResponseEntity<>(patronServiceImp.getAllPatron(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Patron> addPatron(@RequestBody Patron patron){
        Patron newPatron = patronServiceImp.createPatron(patron);
        return new ResponseEntity<>(newPatron, HttpStatus.CREATED);
    }

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
