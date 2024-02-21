package gr.aueb.cf.seminars.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.time.Duration;
import java.util.Date;

@Entity
@Table(name = "scheduling_period")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class SchedulingPeriod extends AbstractEntity {

    @Column(name = "start-date")
    private Date start;

    @Column(name = "end_date")
    private Date end;

    @Formula("DATEDIFF('MINUTE', start_date, end_date)")
    private Integer minutesDuration;

    @Transient
    private String formattedDuration;

    @OneToOne(mappedBy = "schedulingPeriod")
    private Seminar seminar;

    @PostLoad
    private void setFormattedDurationLoad() {
        Duration duration = Duration.between(getStart().toInstant(), getEnd().toInstant());
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();

        if (hours > 0) {
            formattedDuration = hours + " " + ((hours == 1) ? "hour" : "hours ");
        }

        if (minutes > 0) {
            formattedDuration += minutes + " " + ((minutes == 1) ? "minute" : "minutes ");
        }
    }

    public Date getStart() {
        return new Date(start.getTime());
    }

    public Date getEnd() {
        return new Date(end.getTime());
    }

}
