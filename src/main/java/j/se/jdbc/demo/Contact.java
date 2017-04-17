/*
 * Created on Jan 20, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package j.se.jdbc.demo;

/**
 * @author sunilpatil
 * This is DTO class. It will be used for carrying one row of Contact Table
 */
public class Contact {
	int contactId;
	String email;
	String firstName;
	String lastName;

	public Contact() {

	}

	public Contact(int id, String firstName, String lastName, String email) {
		contactId = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return Returns the contactId.
	 */
	public int getContactId() {
		return contactId;
	}

	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param contactId The contactId to set.
	 */
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer contactStr = new StringBuffer();
		contactStr.append("[ContactId=" + contactId);
		contactStr.append(", First Name=" + firstName);
		contactStr.append(", Last Name=" + lastName + "]");
		return contactStr.toString();
	}
}
