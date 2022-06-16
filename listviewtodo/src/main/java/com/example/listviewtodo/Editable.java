package com.example.listviewtodo;

import com.example.listviewtodo.model.Person;

public interface Editable {
    void edit(Person person, int position);
    void insert(Person person);

}
