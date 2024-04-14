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
    @JsonProperty("ITEM_MEDIUM_ID")
    @GeneratedValue(generator = "ITEM_MEDIUM_SEQ")
    @SequenceGenerator(name="ITEM_MEDIUM_SEQ", sequenceName = "ITEM_MEDIUM_SEQ", allocationSize = 1)
    private Long itemMediumId;
    @ManyToOne
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ITEM_ID")
    @JsonProperty("ITEM")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "MEDIUM_ID", referencedColumnName = "MEDIUM_ID")
    @JsonProperty("MEDIUM")
    private Medium medium;
    @JsonProperty("ARCHIVE_STATUS")
    private String archiveStatus;
    @JsonProperty("TYPE")
    private String type;
    @JsonProperty("STATUS")
    private String status;
    @JsonProperty("QUANTITY")
    private Long quantity;
    @JsonProperty("START_CONSUMPTION_DATE")
    private Date startConsumptionDate;
    @JsonProperty("END_CONSUMPTION_DATE")
    private Date endConsumptionDate;
    @JsonProperty("CREATE_DATE")
    private Date createDate;
    @JsonProperty("LAST_MODIFIED")
    private Date lastModified;
}
