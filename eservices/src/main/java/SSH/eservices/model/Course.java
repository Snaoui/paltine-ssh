package SSH.eservices.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Parcours
 */

@Entity
public class Course {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private double duration;
    private double note;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User courseCreator;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Path> paths = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public int getId() {
        return id;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getCourseCreator() {
        return courseCreator;
    }

    public void setCourseCreator(User creator) {
        this.courseCreator = creator;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }
}
