package persistence.src.entity;

import core.annotation.Column;
import core.annotation.Id;
import core.annotation.Table;

@Table("Person")
public class Person {

	@Id
	@Column("id")
	private Integer id;

	@Column("name")
	private String name;

	@Column("nick")
	private String nick;

	@Column("active")
	private Boolean active;

	// Set and Get

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
