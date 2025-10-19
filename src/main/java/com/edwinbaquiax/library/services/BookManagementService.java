package com.edwinbaquiax.library.services;

import com.edwinbaquiax.library.models.entities.Book;
import com.edwinbaquiax.library.models.enums.RentStatus;
import com.edwinbaquiax.library.repositories.AuthorRepository;
import com.edwinbaquiax.library.repositories.BookRepository;
import com.edwinbaquiax.library.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Optional;

@Service
public class BookManagementService implements IBookManagementService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void loadDataModel(DefaultTableModel table) {
        table.setRowCount(0);
        List<Object[]> rows = bookRepository.findAll().stream()
                .map(book -> new Object[]{
                        book.getId(),
                        book.getTitle(),
                        book.getCode(),
                        book.isOk(),
                        book.getAuthor() != null ? book.getAuthor().getName() : "",
                        book.getCategory() != null ? book.getCategory().getName() : ""
                })
                .toList();
        rows.forEach(table::addRow);
    }

    @Override
    public void loadDataModel(DefaultTableModel table, String coincidencia) {
        table.setRowCount(0);
        List<Object[]> rows = bookRepository.findAll().stream()
                .filter(item->item.getTitle().toLowerCase().contains(coincidencia.toLowerCase()))
                .map(book -> new Object[]{
                        book.getId(),
                        book.getTitle(),
                        book.getCode(),
                        book.isOk(),
                        book.getAuthor() != null ? book.getAuthor().getName() : "",
                        book.getCategory() != null ? book.getCategory().getName() : ""
                })
                .toList();
        rows.forEach(table::addRow);
    }
    @Override
    public Book getCurrentItem(JTable table) {
        int indexRow = table.getSelectedRow();
        if (indexRow == -1) return null;

        Long id = ((Number) table.getValueAt(indexRow, 0)).longValue();
        return bookRepository.findById(id).orElse(null);


    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        if (book.getId() == null) throw new IllegalArgumentException("El libro no tiene ID");
        Optional<Book> existing = bookRepository.findById(book.getId());
        if (existing.isPresent()) {
            Book b = existing.get();
            b.setTitle(book.getTitle());
            b.setCode(book.getCode());
            b.setOk(book.isOk());
            b.setAuthor(book.getAuthor());
            b.setCategory(book.getCategory());
            return bookRepository.save(b);
        } else {
            throw new IllegalArgumentException("Libro no encontrado con ID: " + book.getId());
        }
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void LoadAuthors(JComboBox<Object> combo) {
        combo.removeAllItems();
        authorRepository.findAll().forEach(combo::addItem);
    }

    @Override
    public void LoadCategories(JComboBox<Object> combo) {
        combo.removeAllItems();
        categoryRepository.findAll().forEach(combo::addItem);
    }


}