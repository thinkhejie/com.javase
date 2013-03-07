/*
 * Created on Jan 20, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package j.jdbc.demo;

import java.util.List;

/**
 * @author sunilpatil
 * Interface defining all business methods for interacting with CONTACT Table
 */
public interface ContactDAO {
	/*
	 * Method call for deleting contact with supplied contactId
	 */
	public void deleteContact(int contactId);

	/*
	 * Method call for getting contact with supplied contactId 
	 */
	public Contact getContact(int contactId);

	/*
	 * Method call for getting list of existing contacts
	 */
	public List getContacts();

	/*
	 * Method call for inserting new contact
	 */
	public void insertContact(Contact contact);

	/*
	 * Method call for updating contact
	 */
	public void updateContact(Contact contact);
}
