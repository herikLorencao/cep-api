package src.services;

import app.data.repositories.AddressRepository;
import app.entities.Address;
import app.entities.AddressBuilder;
import app.services.AddressService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddressServiceTest {
    @InjectMocks
    private AddressService service;

    @InjectMocks
    private AddressRepository repository;

    private Address buildAddress() {
        return AddressBuilder.anAddress()
                .withZipcode("29375-000")
                .withStreet("")
                .withComplement("")
                .withLocation("Venda Nova do Imigrante")
                .withState("ES")
                .withIbge("3205069")
                .withGia("")
                .withDdd("28")
                .withSiafi("5729")
                .withUpdatedDate(LocalDateTime.now())
                .build();
    }

    public void findCepMock(Address address) {
        doReturn(address).when(repository).findByCep("29375000");
    }

    @Test
    @SneakyThrows
    @Disabled
    public void testFindAddressInDatabase() {
        Address address = buildAddress();
        findCepMock(address);
        Address addressGenerate = service.verify("29375000");
        assertEquals(address, addressGenerate);
    }
}
