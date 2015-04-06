package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import play.mvc.Controller;
import play.mvc.Result;

public class TableLobby extends Controller {

	private static Set<Table> tables;
	
	public static Result createTable(String tableName) {
		Table newTable = new Table(tableName);
		tables.add(newTable);
		return ok("Created table " + tableName + ", id: " + newTable.getId()); //TODO return view of table and wait for other players
	}
	
	public static List<Table> getTablesAsList() {
		return new ArrayList<Table>(tables);
	}
	
}
