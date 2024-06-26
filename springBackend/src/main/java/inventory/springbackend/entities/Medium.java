package inventory.springbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity(name = "MEDIUM")
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Medium {
    @Id
    @JsonProperty("MEDIUM_ID")
    @GeneratedValue(generator = "MEDIUM_SEQ")
    @SequenceGenerator(name="MEDIUM_SEQ", sequenceName = "MEDIUM_SEQ", allocationSize = 1)
    @Column(name = "MEDIUM_ID")
    private Long mediumId;
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
    @ManyToOne
    @JoinColumn(name = "PARENT_MEDIUM", referencedColumnName = "MEDIUM_ID")
    @JsonProperty("PARENT_MEDIUM")
    private Medium parentMedium;
    @JsonProperty("PATH")
    private String path;
    @JsonProperty("CREATE_DATE")
    private Date createDate;
    @JsonProperty("LAST_MODIFIED")
    private Date lastModified;
}
