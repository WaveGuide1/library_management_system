package io.barth.library_management_system.patron;

import java.util.List;
import java.util.Optional;

public interface PatronService {

    public List<Patron> getAllPatron();

    public Patron createPatron(Patron patron);

    public Patron updatePatron(Long id, Patron patron);

    public Optional<Patron> getPatronById(Long id);

    public void deletePatron(Long id);
}
