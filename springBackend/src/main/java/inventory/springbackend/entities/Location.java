package inventory.springbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.Date;

@Entity(name = "LOCATION")
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Location {
    @Id
    @JsonProperty("LOCATION_ID")
    @GeneratedValue(generator = "LOCATION_SEQ")
    @SequenceGenerator(name="LOCATION_SEQ", sequenceName = "LOCATION_SEQ", allocationSize = 1)
    @Column(name = "LOCATION_ID")
    private Long locationId;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("DESCRIPTION")
    private String description;
    @Lob
    @JsonProperty("IMAGE")
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "PARENT_LOCATION", referencedColumnName = "LOCATION_ID")
    @JsonProperty("PARENT_LOCATION")
    private Location parentLocation;
    @JsonProperty("CREATE_DATE")
    private Date createDate;
    @JsonProperty("LAST_MODIFIED")
    private Date lastModified;
}
