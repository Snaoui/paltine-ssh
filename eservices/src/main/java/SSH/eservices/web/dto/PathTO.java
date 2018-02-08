package SSH.eservices.web.dto;

import SSH.eservices.model.Path;

public class PathTO {
    private String userEmail;
    private Path path;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String username) {
        this.userEmail = username;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
