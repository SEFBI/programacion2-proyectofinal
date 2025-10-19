package com.edwinbaquiax.library.services;

import com.edwinbaquiax.library.models.entities.Rent;
import com.edwinbaquiax.library.models.enums.RentStatus;
import com.edwinbaquiax.library.repositories.BookRepository;
import com.edwinbaquiax.library.repositories.ClientRepository;
import com.edwinbaquiax.library.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RentManagementService implements IRentManagementService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void loadDataModelAlquilados(DefaultTableModel table) {
        table.setRowCount(0);
        List<Object[]> rows = rentRepository.findAll().stream()
                .filter(item->item.getStatus() == RentStatus.ALQUILADO)
                .map(rent -> new Object[]{
                        rent.getId(),
                        rent.getRentalDate(),
                        rent.getReturnDate(),
                        rent.getStatus(),
                        rent.getBook() != null ? rent.getBook().getTitle() : "",
                        rent.getClient() != null ? rent.getClient().getName() : ""
                })
                .toList();
        rows.forEach(table::addRow);
    }

    @Override
    public void loadDataModelAlquilados(DefaultTableModel table, String coincidencia) {
        table.setRowCount(0);
        List<Object[]> rows = rentRepository.findAll().stream()
                .filter(item->item.getStatus() == RentStatus.ALQUILADO)
                .filter(item->item.getBook().getTitle().toLowerCase().contains(coincidencia.toLowerCase()))
                .map(rent -> new Object[]{
                        rent.getId(),
                        rent.getRentalDate(),
                        rent.getReturnDate(),
                        rent.getStatus(),
                        rent.getBook() != null ? rent.getBook().getTitle() : "",
                        rent.getClient() != null ? rent.getClient().getName() : ""
                })
                .toList();
        rows.forEach(table::addRow);
    }

    @Override
    public Rent getCurrentItem(JTable table) {
        int indexRow = table.getSelectedRow();
        if (indexRow == -1) return null;

        Long id = ((Number) table.getValueAt(indexRow, 0)).longValue();
        return rentRepository.findById(id).orElse(null);
    }

    @Override
    public Rent save(Rent rent) {
        if (rent.getRentalDate() == null) rent.setRentalDate(new Date());
        return rentRepository.save(rent);
    }

    @Override
    public Rent update(Rent rent) {
        if (rent.getId() == null) throw new IllegalArgumentException("La renta no tiene ID");
        Optional<Rent> existing = rentRepository.findById(rent.getId());
        if (existing.isPresent()) {
            Rent r = existing.get();
            r.setRentalDate(rent.getRentalDate());
            r.setReturnDate(rent.getReturnDate());
            r.setStatus(rent.getStatus());
            r.setBook(rent.getBook());
            r.setClient(rent.getClient());
            return rentRepository.save(r);
        } else {
            throw new IllegalArgumentException("Renta no encontrada con ID: " + rent.getId());
        }
    }

    @Override
    public void delete(Long id) {
        rentRepository.deleteById(id);
    }

    @Override
    public Rent findById(Long id) {
        return rentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    @Override
    public void LoadStatus(JComboBox<Object> combo) {
        combo.removeAllItems();
        List.of(RentStatus.values()).forEach(combo::addItem);


    }

    @Override
    public int totalBooks() {
        return bookRepository.findAll().size();
    }

    @Override
    public int totalBooks(RentStatus rentStatus) {
        return  bookRepository.totalByStateRent(rentStatus).size();
    }

    @Override
    public void loadBooks(JComboBox<Object> combo) {
        combo.removeAllItems();
        var librosAlquilados = bookRepository.totalByStateRent(RentStatus.ALQUILADO);
        bookRepository.findAll()
                .stream()
//                .filter(book->{
//                    return !librosAlquilados.contains(book);
//                })
                .forEach(combo::addItem);
    }

    @Override
    public void loadClients(JComboBox<Object> combo) {
        combo.removeAllItems();
        clientRepository.findAll().forEach(combo::addItem);
    }


}
