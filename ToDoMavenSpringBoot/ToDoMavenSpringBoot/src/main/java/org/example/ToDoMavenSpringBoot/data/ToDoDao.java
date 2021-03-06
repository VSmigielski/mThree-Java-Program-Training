package org.example.ToDoMavenSpringBoot.data;

import org.example.ToDoMavenSpringBoot.models.ToDo;

import java.util.List;

public interface ToDoDao {
    ToDo add(ToDo todo);

    List<ToDo> getAll();

    ToDo findById(int id);

    // true if item exists and is updated
    boolean update(ToDo todo);

    // true if item exists and is deleted
    boolean deleteById(int id);
}
