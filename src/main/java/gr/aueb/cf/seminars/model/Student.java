package gr.aueb.cf.seminars.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student extends AbstractEntity {

    private String firstname;
    private String lastname;

    @ManyToMany
    @Getter(AccessLevel.PROTECTED)
    @JoinTable(
            name = "students_seminars",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "seminar_id", referencedColumnName = "id")
    )
    private Set<Seminar> seminars = new HashSet<>();

    public Set<Seminar> getAllSeminars() {
        return Collections.unmodifiableSet(seminars);
    }

    public void addSeminar(Seminar seminar) {
        seminars.add(seminar);
        seminar.getStudents().add(this);
    }
}
