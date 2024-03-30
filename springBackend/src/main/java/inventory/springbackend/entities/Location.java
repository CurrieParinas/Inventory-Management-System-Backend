package inventory.springbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "LOCATION")
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Location {
    @Id
    @JsonProperty("location_id")
    @GeneratedValue(generator = "location_seq")
    @SequenceGenerator(name="location_seq", sequenceName = "LOCATION_SEQ", allocationSize = 1)
    private Long locationId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @Lob
    @JsonProperty("image")
    private byte[] image;
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("last_modified")
    private Date lastModified;
}
