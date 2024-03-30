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
    @JsonProperty("record_id")
    @GeneratedValue(generator = "record_seq")
    @SequenceGenerator(name="record_seq", sequenceName = "RECORD_SEQ", allocationSize = 1)
    private Long recordId;
    @JsonProperty("item_medium_id")
    private Long itemMediumId;
    @JsonProperty("quantity_change")
    private String quantityChange;
    @JsonProperty("change_date")
    private Date changeDate;
}
