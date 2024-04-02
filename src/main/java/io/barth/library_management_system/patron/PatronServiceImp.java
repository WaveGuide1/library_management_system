package io.barth.library_management_system.patron;

import io.barth.library_management_system.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PatronServiceImp implements PatronService{

    private final PatronRepository patronRepository;

    public PatronServiceImp(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @Override
    public List<Patron> getAllPatron() {
        return patronRepository.findAll();
    }

    @Override
    public Patron createPatron(Patron patron) {
        patron.setCreatedDate(LocalDateTime.now());
        return patronRepository.save(patron);
    }

    @Override
    public Patron updatePatron(Long id, Patron patron) {
        if(!patronRepository.existsById(id)){
            throw new EntityNotFoundException("No Patron with id " + id);
        }
        patron.setId(id);
        patron.setLastModified(LocalDateTime.now());
        return patronRepository.save(patron);
    }

    @Override
    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    @Override
    public void deletePatron(Long id) {
        if(!patronRepository.existsById(id)){
            throw new EntityNotFoundException("No patron with id " + id);
        }
        patronRepository.deleteById(id);
    }
}
