package app.services;

import app.data.repositories.AddressRepository;
import app.data.repositories.ViaCepRepository;
import app.entities.Address;
import app.exceptions.InvalidSearchCepException;
import lombok.SneakyThrows;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;

@Stateless
public class AddressService {
    @Inject
    private AddressRepository databaseRepository;

    @Inject
    private ViaCepRepository viaCepRepository;

    public Address verify(String zipcode) throws InvalidSearchCepException {
        Address address = findInDatabase(zipcode);

        if (address == null)
            return create(zipcode);

        if (!isValidAddress(address))
            return update(address);

        return address;
    }

    private Address create(String zipcode) {
        Address address = findByAPI(zipcode);
        createInDatabase(address);
        return address;
    }

    private Boolean isValidAddress(Address address) {
        LocalDateTime addressDateTimeValidRange = address.getUpdatedDate().plusMinutes(5);
        LocalDateTime nowTime = LocalDateTime.now();
        return nowTime.isBefore(addressDateTimeValidRange);
    }

    private Address findInDatabase(String zipcode) throws InvalidSearchCepException {
        try {
            return databaseRepository.findByCep(zipcode);
        } catch (Exception e) {
            throw new InvalidSearchCepException("Não foi possível acessar a base de dados");
        }
    }

    @SneakyThrows
    private Address findByAPI(String zipcode) {
        try {
            return viaCepRepository.find(zipcode);
        } catch (Exception e) {
            throw new InvalidSearchCepException("Não foi possível realizar a requisição na API");
        }
    }

    private void createInDatabase(Address address) {
        databaseRepository.create(address);
    }

    public Address update(Address address) {
        return databaseRepository.update(address);
    }
}
