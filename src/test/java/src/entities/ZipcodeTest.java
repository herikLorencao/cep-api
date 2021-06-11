package src.entities;

import app.entities.Zipcode;
import app.exceptions.InvalidZipcodeException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZipcodeTest {
    @Test
    @SneakyThrows
    public void testCreateValidZipcodeNoDash() {
        Zipcode zipcode = new Zipcode("29375000");
        String content = zipcode.getContent();
        assertEquals(9, content.length());
    }

    @Test
    @SneakyThrows
    public void testeCreateValidZipcodeWithDash() {
        Zipcode zipcode = new Zipcode("29375-000");
        String content = zipcode.getContent();
        assertEquals(9, content.length());
    }

    @Test
    public void testCreateInvalidZipcode() {
        assertThrows(InvalidZipcodeException.class, () -> new Zipcode("123"));
        assertThrows(InvalidZipcodeException.class, () -> new Zipcode("aaaaaaaa"));
    }
}
