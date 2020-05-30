package ru.academits.dao;

import org.springframework.stereotype.Repository;
import ru.academits.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ContactDao {
    private List<Contact> contactList = new ArrayList<>();
    private AtomicInteger idSequence = new AtomicInteger(0);

    public ContactDao() {
        Contact contact = new Contact();
        contact.setId(getNewId());
        contact.setFirstName("Иван");
        contact.setLastName("Иванов");
        contact.setPhone("9123456789");
        contactList.add(contact);
    }

    private int getNewId() {
        return idSequence.addAndGet(1);
    }

    public List<Contact> getAllContacts() {
        return contactList;
    }

    public void add(Contact contact) {
        contactList.add(contact);
    }

    public void delete(int[] idArray) {
        for (int i = 0; i < contactList.size(); i++) {
            for (int j : idArray) {
                if (contactList.get(i).getId() == j) {
                    contactList.remove(i);
                }
            }
        }
    }

    public List<Contact> getFilteredContacts(String term) {
        List<Contact> filteredList = new ArrayList<>();
        for (Contact c : contactList) {
            if (c.getFirstName().toUpperCase().contains(term) || c.getLastName().toUpperCase().contains(term) ||
                    c.getPhone().toUpperCase().contains(term)) {
                filteredList.add(c);
            }
        }
        return filteredList;
    }
}