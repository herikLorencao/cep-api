package app.data.mappers;

import app.entities.Address;
import app.entities.AddressBuilder;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "address")
@Setter
public class AddressDatabase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String zipcode;
    private String street;
    private String complement;
    private String location;
    private String state;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
    private LocalDateTime updatedDate;

    public void map(Address address) {
        zipcode = address.getZipcode();
        street = address.getStreet();
        complement = address.getComplement();
        location = address.getLocation();
        state = address.getState();
        ibge = address.getIbge();
        gia = address.getGia();
        ddd = address.getDdd();
        siafi = address.getSiafi();
        updatedDate = address.getUpdatedDate();
    }

    public Address parse() {
        return AddressBuilder.anAddress()
                .withZipcode(zipcode)
                .withStreet(street)
                .withComplement(complement)
                .withLocation(location)
                .withState(state)
                .withIbge(ibge)
                .withGia(gia)
                .withDdd(ddd)
                .withSiafi(siafi)
                .withUpdatedDate(updatedDate)
                .build();
    }
}
