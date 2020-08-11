package myApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public enum GraphicCardsDAO {
	instance;

	private Map<Integer, GraphicCards> graphicsCardsMap = new HashMap<Integer, GraphicCards>();

	private GraphicCardsDAO() {
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;

		try {
			// Registering the HSQLDB JDBC driver
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			// Creating the connection with HSQLDB
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "PcParts", "admin");
			if (con != null) {
				System.out.println("Connection created successfully");

			} else {
				System.out.println("Problem with creating connection");
			}

			stmt = con.createStatement();
			result = stmt.executeQuery("SELECT * FROM \"PUBLIC\".\"GPU\"");

			while (result.next()) {
				GraphicCards gpu = new GraphicCards();
				gpu.setId(result.getInt("id"));
				gpu.setName(result.getString("name"));
				gpu.setGpuChip(result.getString("gpuChip"));
				gpu.setRelease(result.getString("release"));
				gpu.setMemory(result.getString("memory"));
				gpu.setGpuClock(result.getInt("gpuClock"));
				gpu.setMemoryClock(result.getInt("memoryClock"));
				gpu.setShaders(result.getInt("shaders"));
				graphicsCardsMap.put(gpu.getId(), gpu);
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

	}

	public void createGraphicCard(GraphicCards gpu) {
		Connection con = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "PcParts", "admin");
			stmt = con.createStatement();
			result = stmt
					.executeUpdate("INSERT INTO GPU(name, gpuChip, memory, release, gpuClock, memoryClock, shaders)\r\n"
							+ "Values(" + "'" + gpu.getName() + "'" + ",'" + gpu.getGpuChip() + "', ' "
							+ gpu.getMemory() + "', '" + gpu.getRelease() + "', " + gpu.getGpuClock() + ","
							+ gpu.getMemoryClock() + "," + gpu.getShaders() + ")");
			con.commit();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println(result + " rows effected");
		System.out.println("Rows inserted successfully");

		graphicsCardsMap.put(gpu.getId(), gpu);
	}

	public void updateGraphicCard(GraphicCards gpu) {
		Connection con = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "PcParts", "admin");
			stmt = con.createStatement();
			result = stmt.executeUpdate("UPDATE GPU " + "SET name = '" + gpu.getName() + "'," + "gpuChip = '"
					+ gpu.getGpuChip() + "'," + "memory = '" + gpu.getMemory() + "'," + "release = '" + gpu.getRelease()
					+ "'," + "gpuClock = " + gpu.getGpuClock() + "," + "memoryClock = " + gpu.getMemoryClock() + ","
					+ "shaders = " + gpu.getShaders() + " WHERE " + "id=" + gpu.getId());
			con.commit();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println(result + " rows effected");
		System.out.println("Rows inserted successfully");

		graphicsCardsMap.put(gpu.getId(), gpu);
	}

	public void deleteGraphicCard(int id) {
		Connection con = null;
		Statement stmt = null;
		int result = 0;

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "PcParts", "admin");
			stmt = con.createStatement();
			result = stmt.executeUpdate("DELETE FROM GPU WHERE id=" + id);
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		System.out.println(result + " Rows effected");

		graphicsCardsMap.remove(id);
	}

	public List<GraphicCards> getGraphicsCards() {
		graphicsCardsMap.clear();

		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;

		try {
			// Registering the HSQLDB JDBC driver
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			// Creating the connection with HSQLDB
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "PcParts", "admin");
			if (con != null) {
				System.out.println("Connection created successfully");

			} else {
				System.out.println("Problem with creating connection");
			}

			stmt = con.createStatement();
			result = stmt.executeQuery("SELECT * FROM \"PUBLIC\".\"GPU\"");

			while (result.next()) {
				GraphicCards gpu = new GraphicCards();
				gpu.setId(result.getInt("id"));
				gpu.setName(result.getString("name"));
				gpu.setGpuChip(result.getString("gpuChip"));
				gpu.setRelease(result.getString("release"));
				gpu.setMemory(result.getString("memory"));
				gpu.setGpuClock(result.getInt("gpuClock"));
				gpu.setMemoryClock(result.getInt("memoryClock"));
				gpu.setShaders(result.getInt("shaders"));
				graphicsCardsMap.put(gpu.getId(), gpu);
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}

		List<GraphicCards> graphicsCards = new ArrayList<GraphicCards>();
		graphicsCards.addAll(graphicsCardsMap.values());
		return graphicsCards;
	}

	public GraphicCards getGraphicsCards(int id) {
		return graphicsCardsMap.get(id);
	}
}
