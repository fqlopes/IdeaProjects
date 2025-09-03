package academy.learnprogramming.service;

import academy.learnprogramming.model.TodoData;
import academy.learnprogramming.model.TodoItem;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ListIterator;

@Service
public class TodoItemServiceImpl implements TodoItemService{

    //fields
    @Getter
    private final TodoData data = new TodoData();

    @Override
    public void addItem(TodoItem toAdd) {
        data.addItem(toAdd);
    }

    @Override
    public void removeItem(int id) {
       data.removeItem(id);
    }

    @Override
    public TodoItem getItem(int id) {
        return data.getItem(id);
    }

    @Override
    public void updateItem(@NonNull TodoItem toUpdate) {
        data.updateItem(toUpdate);
    }
}
