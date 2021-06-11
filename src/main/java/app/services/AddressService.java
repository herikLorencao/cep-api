package app.services;

import app.data.repositories.AddressRepository;
import app.data.repositories.ViaCepRepository;
import app.entities.Address;
import app.entities.Zipcode;
import app.exceptions.InvalidSearchZipcodeException;
import app.exceptions.InvalidZipcodeException;
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

    public Address verify(String zipcode) throws InvalidSearchZipcodeException, InvalidZipcodeException {
        Zipcode zipcodeInfo = new Zipcode(zipcode);
        Address address = findInDatabase(zipcodeInfo);

        if (address == null)
            return create(zipcodeInfo);

        if (!isValidAddress(address))
            return update(zipcodeInfo);

        return address;
    }

    private Address create(Zipcode zipcode) {
        Address address = findByAPI(zipcode.getContent());
        createInDatabase(address);
        return address;
    }

    private Boolean isValidAddress(Address address) {
        LocalDateTime addressDateTimeValidRange = address.getUpdatedDate().plusMinutes(5);
        LocalDateTime nowTime = LocalDateTime.now();
        return nowTime.isBefore(addressDateTimeValidRange);
    }

    private Address findInDatabase(Zipcode zipcode) throws InvalidSearchZipcodeException {
        try {
            return databaseRepository.findByCep(zipcode.getContent());
        } catch (Exception e) {
            throw new InvalidSearchZipcodeException("Não foi possível acessar a base de dados");
        }
    }

    @SneakyThrows
    private Address findByAPI(String zipcode) {
        try {
            return viaCepRepository.find(zipcode);
        } catch (Exception e) {
            throw new InvalidSearchZipcodeException("Não foi possível realizar a requisição na API");
        }
    }

    private void createInDatabase(Address address) {
        databaseRepository.create(address);
    }

    public Address update(Zipcode zipcode)  {
        Address addressApi = findByAPI(zipcode.getContent());
        return databaseRepository.update(addressApi);
    }
}
