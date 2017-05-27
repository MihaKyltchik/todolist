package com.todo.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import com.mysql.fabric.jdbc.FabricMySQLDriver;
import org.springframework.stereotype.Service;

import com.model.Todo;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todoCount = 3;
	private final String URL = "jdbc:mysql//localhost:3306//test";
	private final String USER = "jdbc:mysql//localhost:3306//test";
	private final String PASSWORD = "jdbc:mysql//localhost:3306//test";
	Driver driver;

	public TodoService()  {
		try {
		    driver = new FabricMySQLDriver();
			DriverManager.registerDriver(driver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static List<Todo> getTodos() {
		return todos;
	}

	public Todo retrieveTodo(int id) {
		for (Todo todo : todos) {
			if (todo.getId() == id)
				return todo;
		}
		return null;
	}

	public void updateTodo(Todo todo) {
		todos.remove(todo);
		todos.add(todo);
	}

	public void addTodo(String des, Date targetDate, boolean isDone) {
		Todo todos = new Todo(++todoCount, des, targetDate, isDone);


		try (Connection connection = DriverManager.getConnection(URL,USER,PASSWORD); Statement statement = connection.createStatement()){

			statement.execute("INSERT INTO todo(id , desc, targetDate, isDone) VALUES ()");
		}catch (SQLException e){
			e.printStackTrace();
		}

	}

	public void deleteTodo(int id) {
		Iterator<Todo> iterator = todos.iterator();
		while (iterator.hasNext()) {
			Todo todo = iterator.next();
			if (todo.getId() == id) {
				iterator.remove();
			}
		}
	}
}