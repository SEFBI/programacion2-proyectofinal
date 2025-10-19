package com.edwinbaquiax.library.services;

import com.edwinbaquiax.library.models.entities.Book;
import com.edwinbaquiax.library.models.enums.RentStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IBookManagementService {
    void loadDataModel(DefaultTableModel table);

    void loadDataModel(DefaultTableModel table, String coincidencia);

    Book getCurrentItem(JTable table);

    Book save(Book book);
    Book update(Book book);
    void delete(Long id);
    Book findById(Long id);
    List<Book> findAll();
    void LoadAuthors(JComboBox<Object> combo);
    void LoadCategories(JComboBox<Object> combo);


}
