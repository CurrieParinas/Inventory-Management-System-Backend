package inventory.springbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.Date;

@Entity(name = "ITEM")
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Item {
    @Id
    @JsonProperty("ITEM_ID")
    @GeneratedValue(generator = "ITEM_SEQ")
    @SequenceGenerator(name="ITEM_SEQ", sequenceName = "ITEM_SEQ", allocationSize = 1)
    private Long itemId;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("DESCRIPTION")
    private String description;
    @JsonProperty("BRAND")
    private String brand;
    @JsonProperty("CODENAME")
    private String codename;
    @Lob
    @JsonProperty("IMAGE")
    private Blob image;
    @JsonProperty("QR_CODE")
    private String qrCode;
    @JsonProperty("BAR_CODE")
    private String barCode;
    @JsonProperty("CREATE_DATE")
    private Date createDate;
    @JsonProperty("LAST_MODIFIED")
    private Date lastModified;
}
