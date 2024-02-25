package gr.aueb.cf.seminars.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "teachers")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Teacher extends AbstractEntity {

    @NonNull
    @Column(length = 10, unique = true, nullable = false)
    private String ssn;

    @NonNull
    @Column(length = 50, nullable = false)
    private String firstname;

    @NonNull
    @Column(length = 50, nullable = false)
    private String lastname;

    @OneToMany(mappedBy = "teacher")
    @Getter(AccessLevel.PROTECTED)
    private Set<Seminar> seminars = new HashSet<>();

    public Set<Seminar> getAllSeminars() {
        return Collections.unmodifiableSet(seminars);
    }

    public void addSeminar(Seminar seminar) {
        if (seminar == null || seminar.getTeacher() == this) return;
        seminars.add(seminar);
        seminar.setTeacher(this);
    }
}
