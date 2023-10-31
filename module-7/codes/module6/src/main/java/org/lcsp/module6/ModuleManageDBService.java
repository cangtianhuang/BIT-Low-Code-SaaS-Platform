package org.lcsp.module6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleManageDBService {
	private Connection connection;

	public ModuleManageDBService(String url, String username, String password) {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isModuleExists(String moduleName) {
		try {
			String query = "SELECT COUNT(*) FROM module WHERE name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, moduleName);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			int count = resultSet.getInt(1);
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getModuleVersion(String moduleName) {
		try {
			String query = "SELECT version FROM module WHERE name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, moduleName);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getString("version");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> getModuleDependencies(String moduleName) {
		List<String> dependencies = new ArrayList<>();
		try {
			String query = "SELECT M2.name FROM module AS M1 " +
					"JOIN dependency AS D ON M1.id = D.module_id " +
					"JOIN module AS M2 ON D.depend_module_id = M2.id " +
					"WHERE M1.name = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, moduleName);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				dependencies.add(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dependencies;
	}

	public void storeModule(String moduleName, String artifactId, String version, int creatorId) {
		try {
			String insertQuery = "INSERT INTO module (name, artifact_id, version, creator_id) VALUES (?, ?, ?, ?)";
			PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setString(1, moduleName);
			insertStatement.setString(2, artifactId);
			insertStatement.setString(3, version);
			insertStatement.setInt(4, creatorId);
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateModuleVersion(String moduleName, String newVersion) {
		try {
			String updateQuery = "UPDATE module SET version = ? WHERE name = ?";
			PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
			updateStatement.setString(1, newVersion);
			updateStatement.setString(2, moduleName);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void storeDependency(int projectId, int moduleID, int dependModuleID) {
		try {
			String insertQuery = "INSERT INTO dependency (project_id, module_id, depend_module_id) VALUES (?, ?, ?)";
			PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
			insertStatement.setInt(1, projectId);
			insertStatement.setInt(2, moduleID);
			insertStatement.setInt(3, dependModuleID);
			insertStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
