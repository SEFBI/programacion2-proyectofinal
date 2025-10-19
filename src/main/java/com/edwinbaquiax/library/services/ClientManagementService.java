package com.edwinbaquiax.library.services;

import com.edwinbaquiax.library.models.entities.Client;
import com.edwinbaquiax.library.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

@Service
public class ClientManagementService implements IClientManagementService {

    @Autowired
    ClientRepository clientRepository;


    @Override
    public void loadDataModel(DefaultTableModel table) {
        table.setRowCount(0);
        List<Object[]> rows = clientRepository.findAll().stream()
                .map(item -> new Object[]{
                        item.getId(),
                        item.getName(),
                        item.getLastname(),
                        item.getAddress(),
                        item.getRents().size()
                })
                .toList();

        rows.forEach(table::addRow);
    }
    
      @Override
    public void loadDataModel(DefaultTableModel table,String coincidencia) {
        table.setRowCount(0);
        List<Object[]> rows = clientRepository.findAll().stream()
                .filter(item->item.getName().contains(coincidencia))
                .map(item -> new Object[]{
                        item.getId(),
                        item.getName(),
                        item.getLastname(),
                        item.getAddress(),
                        item.getRents().size()
                })
                .toList();

        rows.forEach(table::addRow);
    }

    @Override
    public Client getCurrentItem(JTable table) {
        int indexRow = table.getSelectedRow();

        Long id = ((Number) table.getValueAt(indexRow, 0)).longValue();
        String nombre = String.valueOf(table.getValueAt(indexRow, 1));
        String apellido = String.valueOf(table.getValueAt(indexRow, 2));
        String direccion = String.valueOf(table.getValueAt(indexRow, 3));

        return  new Client(id,nombre,apellido,direccion);

    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        if (client.getId() == null) {
            throw new IllegalArgumentException("El cliente no tiene ID para actualizar");
        }
        Optional<Client> existing = clientRepository.findById(client.getId());
        if (existing.isPresent()) {
            Client c = existing.get();
            c.setName(client.getName());
            c.setLastname(client.getLastname());
            c.setAddress(client.getAddress());
            return clientRepository.save(c);
        } else {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + client.getId());
        }
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }


}
