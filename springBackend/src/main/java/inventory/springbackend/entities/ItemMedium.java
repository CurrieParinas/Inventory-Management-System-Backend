package inventory.springbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "ITEM_MEDIUM")
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ItemMedium {
    @Id
    @JsonProperty("item_medium_id")
    @GeneratedValue(generator = "item_medium_seq")
    @SequenceGenerator(name="item_medium_seq", sequenceName = "ITEM_MEDIUM_SEQ", allocationSize = 1)
    private Long itemMediumId;
    @JsonProperty("item_id")
    private Long itemId;
    @JsonProperty("medium_id")
    private Long mediumId;
    @JsonProperty("archive_status")
    private String archiveStatus;
    @JsonProperty("type")
    private String type;
    @JsonProperty("status")
    private String status;
    @JsonProperty("quantity")
    private Long quantity;
    @JsonProperty("start_consumption_date")
    private Date startConsumptionDate;
    @JsonProperty("end_consumption_date")
    private Date endConsumptionDate;
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("last_modified")
    private Date lastModified;
}
