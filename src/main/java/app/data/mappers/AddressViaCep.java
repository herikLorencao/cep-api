package app.data.mappers;

import app.entities.Address;
import app.entities.AddressBuilder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressViaCep {
    private String cep;
    private String logradouro;
    private String complemento;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    public Address parse() {
        return AddressBuilder.anAddress()
                .withZipcode(cep)
                .withStreet(logradouro)
                .withComplement(complemento)
                .withLocation(localidade)
                .withState(uf)
                .withIbge(ibge)
                .withGia(gia)
                .withDdd(ddd)
                .withSiafi(siafi)
                .withUpdatedDate(LocalDateTime.now())
                .build();
    }
}
