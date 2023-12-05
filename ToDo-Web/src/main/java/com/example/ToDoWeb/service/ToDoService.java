package com.example.ToDoWeb.service;

import com.example.ToDoWeb.model.ToDo;
import com.example.ToDoWeb.repo.IToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService {
    @Autowired
    IToDoRepo repo;
    public List<ToDo> getAllToDoItems(){
        ArrayList<ToDo> todolist = new ArrayList<>();
        repo.findAll().forEach(todo -> todolist.add(todo));
        return todolist;
    }
    public ToDo getToDoItem(Long id){
        return repo.findById(id).get();
    }
    public Boolean updateStatus(Long id){
        ToDo todo = getToDoItem(id);
        todo.setStatus("Complete Update");
        return saveOrUpdateToDoItem(todo);
    }
    public Boolean saveOrUpdateToDoItem(ToDo todo){
        ToDo updateItem = repo.save(todo);
        if (getToDoItem(updateItem.getId())!=null){
            return true;
        }
        return false;
    }
    public Boolean deleteToDoItem(Long id){
        repo.deleteById(id);
        if (repo.findById(id)!=null){
            return true;
        }
        return false;
    }
}
