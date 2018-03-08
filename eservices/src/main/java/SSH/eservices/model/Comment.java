package SSH.eservices.model;

import javax.persistence.*;

/**
 * Commentaire
 */

@Entity
public class Comment {
    @Id
    @GeneratedValue
    int id;
    String content;

    @ManyToOne
    Course course;

    @ManyToOne
    private User author;


    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Course getCourse() { return course; }

    public void setCourse(Course course) { this.course = course; }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
