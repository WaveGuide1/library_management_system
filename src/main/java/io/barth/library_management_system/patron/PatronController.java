package io.barth.library_management_system.patron;

import io.barth.library_management_system.exception.GeneralExceptionHandler;
import io.barth.library_management_system.exception.RecordNotFoundException;
import io.barth.library_management_system.exception.UserAlreadyRegisterException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/patrons")
@Validated
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
        } catch (Exception ex){
            throw new RecordNotFoundException("No available record");
        }
    }

    // Save a patron
    @PostMapping
    public ResponseEntity<Patron> addPatron(@Valid @RequestBody Patron patron){
        try {
            Patron newPatron = patronServiceImp.createPatron(patron);
            return new ResponseEntity<>(newPatron, HttpStatus.CREATED);
        } catch (UserAlreadyRegisterException e){
            throw e;
        } catch (Exception e){
            throw new GeneralExceptionHandler("Something went wrong");
        }
    }

    // Update patron info
    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(
            @Valid @PathVariable Long id, @RequestBody Patron patron
    ){
        try {
            Patron updatedPatron = patronServiceImp.updatePatron(id, patron);
            return new ResponseEntity<>(updatedPatron, HttpStatus.CREATED);
        } catch (RecordNotFoundException ex){
            throw ex;
        } catch (Exception ex){
            throw new GeneralExceptionHandler("Something went wrong");
        }
    }

    // Get patron by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(@PathVariable Long id) {

        try {
            Patron patron = patronServiceImp.getPatronById(id);
            return new ResponseEntity<>(patron, HttpStatus.OK);
        } catch (GeneralExceptionHandler ex) {
            throw new GeneralExceptionHandler("Something went wrong");
        }

    }

    // Delete a patron
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id){
        try {
            patronServiceImp.deletePatron(id);
            return ResponseEntity.noContent().build();
        } catch (RecordNotFoundException ex){
            throw ex;
        }catch (Exception ex){
            throw new GeneralExceptionHandler("Something went wrong");
        }
    }
}
