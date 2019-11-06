package persistence.src;

import core.Crud;
import entity.Person;

public class Main {

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

		Crud crud = new Crud(Person.class);
		System.out.println(crud.select("2"));

		Person person = new Person();
		person.setId(1);
		person.setName("Fulano");
		person.setNick("Ful");
		person.setActive(true);
		System.out.println(crud.insert(person));

	}

}
