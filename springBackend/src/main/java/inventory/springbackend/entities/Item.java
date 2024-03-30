package inventory.springbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "ITEM")
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Item {
    @Id
    @JsonProperty("item_id")
    @GeneratedValue(generator = "item_seq")
    @SequenceGenerator(name="item_seq", sequenceName = "ITEM_SEQ", allocationSize = 1)
    private Long itemId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("codename")
    private String codename;
    @Lob
    @JsonProperty("image")
    private byte[] image;
    @JsonProperty("qr_code")
    private String qrCode;
    @JsonProperty("bar_code")
    private String barCode;
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("last_modified")
    private Date lastModified;
}
