package com.edwinbaquiax.library.services;

import com.edwinbaquiax.library.models.entities.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public interface IClientManagementService {

    void loadDataModel(DefaultTableModel table);
    void loadDataModel(DefaultTableModel table,String coincidencia);
    Client getCurrentItem(JTable table);
    Client save(Client client);
    Client update(Client client);
    void delete(Long id);
    Client findById(Long id);


}
