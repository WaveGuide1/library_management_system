package io.barth.library_management_system.patron;

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
        Patron updatedPatron = patronServiceImp.updatePatron(id, patron);
        return new ResponseEntity<>(updatedPatron, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatron(@PathVariable Long id){

        return patronServiceImp.getPatronById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id){
        patronServiceImp.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}
