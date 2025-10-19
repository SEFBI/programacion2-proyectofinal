package com.edwinbaquiax.library.services;

import com.edwinbaquiax.library.models.entities.Rent;
import com.edwinbaquiax.library.models.enums.RentStatus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface IRentManagementService {
    void loadDataModelAlquilados(DefaultTableModel table);
    void loadDataModelAlquilados(DefaultTableModel table, String coincidencia);
    Rent getCurrentItem(JTable table);

    Rent save(Rent rent);
    Rent update(Rent rent);
    void delete(Long id);
    Rent findById(Long id);
    List<Rent> findAll();
    void loadClients(JComboBox<Object> combo);
    void loadBooks(JComboBox<Object> combo);
    void LoadStatus(JComboBox<Object> combo);
    int totalBooks();
    int totalBooks(RentStatus rentStatus);
}
