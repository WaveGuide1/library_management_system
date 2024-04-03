package io.barth.library_management_system.patron;

import io.barth.library_management_system.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays; import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatronControllerTest {
    @Mock
    private PatronServiceImp patronServiceImp;
    @InjectMocks
    private PatronController patronController;
    @Test public void testGetAllPatrons_Success() {
        // Mock data
        Patron patron1 = new Patron(1L, "Moo", "Foo", "john@example.com", "1234567890", "54 Okpanam", null, null);
        Patron patron2 = new Patron(2L, "Jane", "Smith", "jane@example.com", "12345676540", "54 Okpanam", null, null);
        List<Patron> patrons = Arrays.asList(patron1, patron2);
        when(patronServiceImp.getAllPatron()).thenReturn(patrons);
        // Test
        ResponseEntity<List<Patron>> response = patronController.getPatrons();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }
    @Test
    public void testGetAllPatrons_EmptyList() {
        // Mock data
        List<Patron> patrons = List.of();
        when(patronServiceImp.getAllPatron()).thenReturn(patrons);
        // Test
        ResponseEntity<List<Patron>> response = patronController.getPatrons();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, Objects.requireNonNull(response.getBody()).size());
    }
    @Test
    public void testGetPatronById_Success() {
        // Mock data
        Long patronId = 1L;
        Patron patron = new Patron(patronId, "Moo", "Foo", "john@example.com", "1234567890", "54 Okpanam", null, null);
        when(patronServiceImp.getPatronById(patronId)).thenReturn(patron);
        // Test
        ResponseEntity<Patron> response = patronController.getPatronById(patronId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patron, response.getBody());
    }
    @Test
    public void testGetPatronById_NotFound() {
        // Mock data
        Long patronId = 1L;
        when(patronServiceImp.getPatronById(patronId))
                .thenThrow(new EntityNotFoundException("Patron not found"));
        // Test
        ResponseEntity<Patron> response = patronController.getPatronById(patronId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testAddPatron_Success() {
        // Mock data
        Patron patron = new Patron(1L, "Moo", "Foo", "john@example.com", "1234567890", "54 Okpanam", null, null);
        when(patronServiceImp.createPatron(patron)).thenReturn(patron);
        // Test
        ResponseEntity<Patron> response = patronController.addPatron(patron);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(patron, response.getBody());
    }
    @Test
    public void testUpdatePatron_Success() {
        // Mock data
        Long patronId = 1L;
        Patron updatedPatron = new Patron(patronId, "Moo", "Foo", "john@example.com", "1234567890", "54 Okpanam", null, null);
        when(patronServiceImp.updatePatron(patronId, updatedPatron))
                .thenReturn(updatedPatron);
        // Test
        ResponseEntity<Patron> response = patronController.updatePatron(patronId, updatedPatron);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(updatedPatron, response.getBody());
    }
    @Test
    public void testUpdatePatron_NotFound() {
        // Mock data
        Long patronId = 1L;
        Patron updatedPatron = new Patron(1L, "Update", "Foo", "john@example.com", "1234567890", "54 Okpanam", null, null);
        when(patronServiceImp.updatePatron(patronId, updatedPatron))
                .thenThrow(new EntityNotFoundException("Patron not found"));
        // Test
        ResponseEntity<Patron> response = patronController.updatePatron(patronId, updatedPatron);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testDeletePatron_Success() {
        // Mock data
        Long patronId = 1L;
        doNothing().when(patronServiceImp).deletePatron(patronId);
        // Test
        ResponseEntity<Void> response = patronController.deletePatron(patronId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    } @Test public void testDeletePatron_NotFound() {
        // Mock data
        Long patronId = 1L;
        doThrow(new EntityNotFoundException("Patron not found"))
                .when(patronServiceImp).deletePatron(patronId);
        // Test
        ResponseEntity<Void> response = patronController.deletePatron(patronId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
