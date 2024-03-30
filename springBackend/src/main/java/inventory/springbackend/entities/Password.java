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
    @JsonProperty("password_id")
    @GeneratedValue(generator = "password_seq")
    @SequenceGenerator(name="password_seq", sequenceName = "PASSWORD_SEQ", allocationSize = 1)
    private Long passwordId;
    @JsonProperty("password")
    private String password;
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("last_modified")
    private Date lastModified;
}
