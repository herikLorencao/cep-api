package app.data.repositories;

import app.data.mappers.AddressDatabase;
import app.entities.Address;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class AddressRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Address findByCep(String zipcode) {
        AddressDatabase addressData = findDataByCep(zipcode);

        if (addressData == null)
            return null;

        return addressData.parse();
    }

    public void create(Address address) {
        AddressDatabase addressDatabase = new AddressDatabase();
        addressDatabase.map(address);
        entityManager.persist(addressDatabase);
    }

    public Address update(Address address) {
        AddressDatabase addressDatabase = findDataByCep(address.getZipcode());
        addressDatabase.map(address);
        AddressDatabase updatedAddressDatabase = entityManager.merge(addressDatabase);
        return updatedAddressDatabase.parse();
    }

    private AddressDatabase findDataByCep(String zipcode) {
        TypedQuery<AddressDatabase> query = entityManager
                .createQuery("SELECT a FROM AddressDatabase a WHERE a.zipcode = :zipcode", AddressDatabase.class);
        query.setParameter("zipcode", zipcode);
        List<AddressDatabase> results = query.getResultList();

        if (results.isEmpty()) return null;
        return results.get(0);
    }
}