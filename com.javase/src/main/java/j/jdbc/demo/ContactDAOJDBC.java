package j.jdbc.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunilpatil
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ContactDAOJDBC implements ContactDAO {
	/* (non-Javadoc)
	 * @see com.sample.dao.ContactDAO#deleteContact(int)
	 */
	@Override
	public void deleteContact(int contactId) {
		System.out.println("Inside ContactDAOJDBC.deleteContact() method");
	}

	/* (non-Javadoc)
	 * @see com.sample.dao.ContactDAO#getContact(int)
	 */
	@Override
	public Contact getContact(int contactId) {
		System.out.println("Inside ContactDAOJDBC.getContact() method");
		return new Contact();
	}

	/* (non-Javadoc)
	 * @see com.sample.dao.ContactDAO#getContacts()
	 */
	@Override
	public List getContacts() {
		System.out.println("Inside ContactDAOJDBC.getContacts() method");
		return new ArrayList();
	}

	/* (non-Javadoc)
	 * @see com.sample.dao.ContactDAO#insertContact(com.sample.Contact)
	 */
	@Override
	public void insertContact(Contact contact) {
		System.out.println("Inside ContactDAOJDBC.insertContact() method");
	}

	/* (non-Javadoc)
	 * @see com.sample.dao.ContactDAO#updateContact(com.sample.Contact)
	 */
	@Override
	public void updateContact(Contact contact) {
		System.out.println("Inside ContactDAOJDBC.updateContact() method");
	}

}