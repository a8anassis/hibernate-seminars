package gr.aueb.cf.seminars.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "rooms")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Room extends AbstractEntity {

    @NonNull
    @Column(unique = true, nullable = false)
    private String title;

    @OneToMany(mappedBy = "room")
    @Getter(AccessLevel.PRIVATE)
    private Set<Seminar> seminars = new HashSet<>();

    public Set<Seminar> getAllSeminars() {
        return Collections.unmodifiableSet(seminars);
    }

    public void addSeminar(Seminar seminar) {
        seminars.add(seminar);
        seminar.setRoom(this);
    }
}
