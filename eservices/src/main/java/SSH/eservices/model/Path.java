package SSH.eservices.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.geo.Point;

/**
 * Trajethr
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
    private int index = -1;
    private boolean isVisited = false;
    private boolean isBeginning = false;
    private boolean isFinish = false;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Survey> surveys = new ArrayList<>();

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


    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public boolean isBeginning() {
        return isBeginning;
    }

    public void setBeginning(boolean begin) {
        isBeginning = begin;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
