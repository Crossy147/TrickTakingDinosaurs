package controllers;

import java.util.UUID;

public class Table {
	private final UUID id;
	private final String name;
	
	public Table(String name) {
		id = UUID.randomUUID();
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Table)) {
			return false;
		}	
		Table t = (Table)o;
		return id.equals(t.id);
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
}