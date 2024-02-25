package gr.aueb.cf.seminars.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "teams_room")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class TeamsRoom extends Room {

    @NonNull
    @Column(name = "teams_code", length = 6, unique = true, nullable = false)
    private String teamsCode;

    @Column(name = "teams_url", length = 512)
    private String teamsURL;
}
