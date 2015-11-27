package j.jdbc.demo;

import java.util.ArrayList;
import java.util.List;

public class ContactDAOHibernate implements ContactDAO {
	@Override
	public void deleteContact(int contactId) {
		System.out.println("Inside ContactDAOHibernate.deleteContact() method");
	}

	@Override
	public Contact getContact(int contactId) {
		System.out.println("Inside ContactDAOHibernate.getContact() method");
		return new Contact();
	}

	@Override
	public List getContacts() {
		System.out.println("Inside ContactDAOHibernate.getContacts() method");
		return new ArrayList();
	}

	@Override
	public void insertContact(Contact contact) {
		System.out.println("Inside ContactDAOHibernate.insert() method");
	}

	@Override
	public void updateContact(Contact contact) {
		System.out.println("Inside ContactDAOHibernate.updateContact() method");
	}
}
