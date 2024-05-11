package io.barth.library_management_system.patron;


import io.barth.library_management_system.exception.RecordNotFoundException;
import io.barth.library_management_system.exception.UserAlreadyRegisterException;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
@Transactional
public class PatronServiceImp implements PatronService{

    private final PatronRepository patronRepository;

    public PatronServiceImp(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    // Get all patrons
    @Override
    public List<Patron> getAllPatron() {

        return patronRepository.findAll();
    }

    // Add a patrons
    @Override
    public Patron createPatron(Patron patron) {
        boolean oldPatron = patronRepository.findByEmail(patron.getEmail()).isPresent();
        if(oldPatron){
            throw new UserAlreadyRegisterException("Patron with email address of: " +
                    patron.getEmail() + " already exist");
        }
        patron.setCreatedDate(LocalDateTime.now());
        return patronRepository.save(patron);
    }

    // Update a patron
    @Override
    public Patron updatePatron(Long id, Patron patron) {
        if(!patronRepository.existsById(id)){
            throw new RecordNotFoundException("No Patron with id " + id);
        }
        patron.setId(id);
        patron.setLastModified(LocalDateTime.now());
        return patronRepository.save(patron);
    }

    // Get patron by ID
    @Override
    public Patron getPatronById(Long id) {

        return patronRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Patron not found with id: " + id));
}

    // Delete a patrons
    @Override
    public void deletePatron(Long id) {
        if(!patronRepository.existsById(id)){
            throw new RecordNotFoundException("No patron with id " + id);
        }
        patronRepository.deleteById(id);
    }
}
