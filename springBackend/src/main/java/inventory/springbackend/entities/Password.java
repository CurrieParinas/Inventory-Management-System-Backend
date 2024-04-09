package inventory.springbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "PASSWORD")
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Password {
    @Id
    @JsonProperty("PASSWORD_ID")
    @GeneratedValue(generator = "PASSWORD_SEQ")
    @SequenceGenerator(name="PASSWORD_SEQ", sequenceName = "PASSWORD_SEQ", allocationSize = 1)
    private Long passwordId;
    @JsonProperty("PASSWORD")
    private String password;
    @JsonProperty("CREATE_DATE")
    private Date createDate;
    @JsonProperty("LAST_MODIFIED")
    private Date lastModified;
}
