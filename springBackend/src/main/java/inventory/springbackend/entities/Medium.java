package inventory.springbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "MEDIUM")
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Medium {
    @Id
    @JsonProperty("medium_id")
    @GeneratedValue(generator = "medium_seq")
    @SequenceGenerator(name="medium_seq", sequenceName = "MEDIUM_SEQ", allocationSize = 1)
    private Long mediumId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @Lob
    @JsonProperty("image")
    private byte[] image;
    @JsonProperty("qr_code")
    private String qrCode;
    @JsonProperty("parent_medium_id")
    private Long parentMediumId;
    @JsonProperty("path")
    private String path;
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("last_modified")
    private Date lastModified;
}
