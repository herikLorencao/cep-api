package app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.json.bind.annotation.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Address implements Serializable {
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
    @JsonIgnore
    private LocalDateTime updatedDate;
}
