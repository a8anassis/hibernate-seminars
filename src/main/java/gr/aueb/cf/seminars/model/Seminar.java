package gr.aueb.cf.seminars.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "seminars")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Seminar extends AbstractEntity {

    @NonNull
    @Column(length = 256, nullable = false, unique = true)
    private String title;

    @Column(length = 512)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToMany(mappedBy = "seminars")
//    @JoinTable(name = "seminars_students",
//            joinColumns = @JoinColumn(name = "seminar_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    @Getter(AccessLevel.PROTECTED)
    private Set<Student> students = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "scheduling_id", referencedColumnName = "id")
    private SchedulingPeriod schedulingPeriod;

    public void addStudent(Student student) {
        students.add(student);
        student.getSeminars().add(this);
    }
}
