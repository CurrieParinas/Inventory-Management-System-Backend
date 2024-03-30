package inventory.springbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "SUBLOCATION")
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Sublocation {
    @Id
    @JsonProperty("sublocation_id")
    @GeneratedValue(generator = "sublocation_seq")
    @SequenceGenerator(name="sublocation_seq", sequenceName = "SUBLOCATION_SEQ", allocationSize = 1)
    private Long sublocationId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @Lob
    @JsonProperty("image")
    private byte[] image;
    @JsonProperty("location_id")
    private Long locationId;
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("last_modified")
    private Date lastModified;
}
