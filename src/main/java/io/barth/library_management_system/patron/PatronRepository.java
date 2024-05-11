package io.barth.library_management_system.patron;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatronRepository extends JpaRepository<Patron, Long> {

    Optional<Patron>  findByEmail(String email);
}
