package inventory.springbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "RECORD")
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Record {
    @Id
    @JsonProperty("RECORD_ID")
    @GeneratedValue(generator = "RECORD_SEQ")
    @SequenceGenerator(name="RECORD_SEQ", sequenceName = "RECORD_SEQ", allocationSize = 1)
    private Long recordId;
    @JsonProperty("ITEM_MEDIUM_ID")
    private Long itemMediumId;
    @JsonProperty("QUANTITY_CHANGE")
    private String quantityChange;
    @JsonProperty("CHANGE_DATE")
    private Date changeDate;
}
