package SSH.eservices.model;


import java.util.List;

import javax.persistence.*;

/**
 * Parcours
 */

@Entity
public class Course {
    @Id
    private int id;
    private String name;

    @OneToOne
    private Path path;
    private double duration;
    private double note;
    @OneToMany
    private  List<Comment> comments;
    @ManyToOne
    private User courseCreator;
    @OneToMany
    List<Path> paths;

    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
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

    public List<Path> getPaths() { return paths; }

    public void setPaths(List<Path> paths) { this.paths = paths; }
}
