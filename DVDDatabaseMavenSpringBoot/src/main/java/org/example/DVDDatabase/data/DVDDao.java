package org.example.DVDDatabase.data;
import java.util.List;
import org.example.DVDDatabase.models.DVD;
public interface DVDDao {
    DVD add(DVD dvd);

    List<DVD> getAll();

    DVD findById(int id);

    // true if item exists and is updated
    boolean update(DVD dvd);

    // true if item exists and is deleted
    boolean deleteById(int id);

}
