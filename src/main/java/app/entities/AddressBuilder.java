package app.entities;

import java.time.LocalDateTime;

public final class AddressBuilder {
    private String zipcode;
    private String street;
    private String complement;
    private String district;
    private String location;
    private String state;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
    private LocalDateTime updatedDate;

    private AddressBuilder() {
    }

    public static AddressBuilder anAddress() {
        return new AddressBuilder();
    }

    public AddressBuilder withZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public AddressBuilder withStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressBuilder withComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public AddressBuilder withDistrict(String district) {
        this.district = district;
        return this;
    }

    public AddressBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public AddressBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public AddressBuilder withIbge(String ibge) {
        this.ibge = ibge;
        return this;
    }

    public AddressBuilder withGia(String gia) {
        this.gia = gia;
        return this;
    }

    public AddressBuilder withDdd(String ddd) {
        this.ddd = ddd;
        return this;
    }

    public AddressBuilder withSiafi(String siafi) {
        this.siafi = siafi;
        return this;
    }

    public AddressBuilder withUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public Address build() {
        Address address = new Address();
        address.setZipcode(zipcode);
        address.setStreet(street);
        address.setComplement(complement);
        address.setDistrict(district);
        address.setLocation(location);
        address.setState(state);
        address.setIbge(ibge);
        address.setGia(gia);
        address.setDdd(ddd);
        address.setSiafi(siafi);
        address.setUpdatedDate(updatedDate);
        return address;
    }
}
