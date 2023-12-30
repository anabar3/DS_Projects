package e3;

import java.util.Objects;

public record Track(String side, String recording , String artist, String title, int duration) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(recording, track.recording);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recording);
    }
}
