package sayant.projects.eventscmecservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.OffsetDateTime;

/**
 * Created by sayantjm on 17/12/20
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank
    String username;

    @NotBlank
    String name;

    @NotBlank
    String surname;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    @Null
    private OffsetDateTime createdDate;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    @Null
    private OffsetDateTime updatedDate;
}
