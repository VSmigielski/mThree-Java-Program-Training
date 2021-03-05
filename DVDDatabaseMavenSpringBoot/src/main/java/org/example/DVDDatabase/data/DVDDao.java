package org.example.DVDDatabase.data;
import java.util.List;
import org.example.DVDDatabase.models.DVD;
public interface DVDDao {
    // Create
    DVD add(DVD dvd);

    List<DVD> getAll();

    // Retrievals
    DVD findByDVDId(int dvdId);
    List <DVD> findByTitle(String title);

    List <DVD> findByReleaseYear(int releaseYear);

    List <DVD> findByDirector(String director);

    List <DVD> findByRating(String rating);

    // Update
    // true if item exists and is updated
    boolean update(DVD dvdId);

    // Delete
    // true if item exists and is deleted
    boolean deleteByDVDId(int dvdId);

}
