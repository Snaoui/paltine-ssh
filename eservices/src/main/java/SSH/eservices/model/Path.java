package SSH.eservices.model;

import java.util.ArrayList;
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
    private int id;
    @Column(name = "pointTo")
    private Point to;
    @Column(name = "pointFrom")
    private Point from;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Survey> surveys = new ArrayList<>();;

    public int getId() {
        return id;
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
