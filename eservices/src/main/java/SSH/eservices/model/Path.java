package SSH.eservices.model;

import java.util.List;

import javax.persistence.*;

import org.springframework.data.geo.Point;

/**
 * Trajet
 */
@Entity(name = "trajet")
public class Path {
    @Id
    @GeneratedValue
    int id;
    @Column(name = "pointTo")
    Point to;
    @Column(name = "pointFrom")
    Point from;
    @OneToMany
    List<Survey> surveys;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getTo() {
        return to;
    }

    public void setTo(Point to) {
        this.to = to;
    }

    public Point getFrom() {
        return from;
    }

    public void setFrom(Point from) {
        this.from = from;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }
}
