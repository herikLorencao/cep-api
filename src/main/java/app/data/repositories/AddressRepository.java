package app.data.repositories;

import app.data.mappers.AddressDatabase;
import app.entities.Address;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class AddressRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Address findByCep(String cep) {
        TypedQuery<AddressDatabase> query = entityManager
                .createQuery("SELECT a FROM AddressDatabase a WHERE a.zipcode = :zipcode", AddressDatabase.class);
        query.setParameter("zipcode", cep);
        List<AddressDatabase> results = query.getResultList();

        if (results.isEmpty()) return null;
        return results.get(0).parse();
    }

    public void create(Address address) {
        AddressDatabase addressDatabase = new AddressDatabase();
        addressDatabase.map(address);
        entityManager.persist(addressDatabase);
    }


    public Address update(Address address) {
        AddressDatabase addressDatabase = new AddressDatabase();
        addressDatabase.map(address);
        AddressDatabase updatedAddressDatabase = entityManager.merge(addressDatabase);
        return addressDatabase.parse();
    }
}