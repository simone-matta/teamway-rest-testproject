package io.teamway.testproject.teamwayresttest.entity;

import io.teamway.testproject.teamwayresttest.domain.Shift;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TimeSchedule {

    @Id
    @GeneratedValue
    private Long id;

    private Long workerId;

    private Shift shift;

    private LocalDate date;

    public TimeSchedule() {
    }

    public TimeSchedule(Long workerId, Shift shift, LocalDate date) {
        this.workerId = workerId;
        this.shift = shift;
        this.date = date;
    }

    public TimeSchedule(Long workerId, LocalDateTime dateTime) {
        this(workerId, Shift.of(dateTime), dateTime.toLocalDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TimeSchedule{" +
                "id=" + id +
                ", workerId=" + workerId +
                ", shift=" + shift +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeSchedule that = (TimeSchedule) o;
        return Objects.equals(id, that.id) && Objects.equals(workerId, that.workerId) && shift == that.shift && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, workerId, shift, date);
    }
}
