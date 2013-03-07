package j.se.collection;

public class Person implements Comparable<Person> {
	private String name;
	private int age;

	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return name + age;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Person == false)
			return false;
		Person obj = (Person) o;
		return name.equals(obj.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public int compareTo(Person o) {
		return this.name.compareTo(o.name) * 100 + (this.age - o.age);
	}
}
