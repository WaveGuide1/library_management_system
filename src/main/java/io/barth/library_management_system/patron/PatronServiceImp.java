package io.barth.library_management_system.patron;

import io.barth.library_management_system.exception.BadRequestException;
import io.barth.library_management_system.exception.EntityNotFoundException;
import io.barth.library_management_system.exception.InternalServerErrorException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PatronServiceImp implements PatronService{

    private static final Logger logger = Logger.getLogger(PatronService.class.getName());

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
        if (patron.getFirstName() == null || patron.getLastName() == null ||
        patron.getEmail() == null || patron.getAddress() == null) {
            throw new BadRequestException("First name, Last name, Email and address are required for creating a patron");
        }
        patron.setCreatedDate(LocalDateTime.now());
        logger.info("Patron added successfully: " + patron);
        return patronRepository.save(patron);
    }

    // Update a patron
    @Override
    public Patron updatePatron(Long id, Patron patron) {
        if(!patronRepository.existsById(id)){
            throw new EntityNotFoundException("No Patron with id " + id);
        }
        patron.setId(id);
        patron.setLastModified(LocalDateTime.now());
        logger.info("Patron updated successfully: " + patron);
        return patronRepository.save(patron);
    }

    // Get patron by ID
    @Override
    public Patron getPatronById(Long id) {

        logger.info("Getting patron by id: " + id);
        Patron patron = patronRepository.findById(id)
                .orElseThrow(() -> { logger.severe("Patron not found with id: " + id);
        return new InternalServerErrorException("An internal server error occurred while retrieving the patron with id: " + id);
    });
        logger.info("Patron retrieved successfully: " + patron);
        return patron;
}

    // Delete a patrons
    @Override
    public void deletePatron(Long id) {
        if(!patronRepository.existsById(id)){
            throw new EntityNotFoundException("No patron with id " + id);
        }
        logger.info("Patron deleted successfully. The id is : " + id);
        patronRepository.deleteById(id);
    }
}
