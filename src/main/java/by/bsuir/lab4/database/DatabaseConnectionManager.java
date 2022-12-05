package by.bsuir.lab4.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Properties;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class DatabaseConnectionManager {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final ReentrantLock connectionLock = new ReentrantLock();
    private static final ReentrantLock returnLock = new ReentrantLock();
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static DatabaseConnectionManager INSTANCE;
    private Deque<Connection> connections;
    private Semaphore semaphore;
    private int connectionSize;

    private DatabaseConnectionManager() {
        initConnections();
    }

    public static DatabaseConnectionManager getInstance() {
        if (!initialized.get()) {
            try {
                lock.lock();
                if (!initialized.get()) {
                    INSTANCE = new DatabaseConnectionManager();
                    initialized.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return INSTANCE;
    }
    private void initConnections() {
        try {
            Class<? extends DatabaseConnectionManager> aClass = this.getClass();
            ClassLoader classLoader = aClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("db.properties");

            Properties property = new Properties();
            property.load(inputStream);

            connectionSize = Integer.parseInt(property.getProperty("db.connectionSize"));

            connections = new ArrayDeque<>();
            semaphore = new Semaphore(connectionSize);

            for (int i = 0; i < connectionSize; i++) {
                Connection connection;
                try {
                    String url = property.getProperty("db.url");
                    String name = property.getProperty("db.name");
                    String password = property.getProperty("db.password");
                    String driver = property.getProperty("db.driver");

                    Class.forName(driver);
                    connection = DriverManager.getConnection(url, name, password);
                } catch (SQLException e) {
                    throw new IllegalArgumentException();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                connections.push(connection);
            }
            if (connections.isEmpty()) {
                throw new IllegalArgumentException("Connections are not created!");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found" + e.getMessage(), e);
        }
    }
    public Connection getConnection() {
        try {
            connectionLock.lock();
            semaphore.acquire();
            return connections.pop();
        } catch (InterruptedException e) {
            throw new IllegalArgumentException();
        } finally {
            connectionLock.unlock();
        }
    }
    public void returnConnection(Connection connection) {
        try {
            returnLock.lock();
            connections.push(connection);
            semaphore.release();
        } finally {
            returnLock.unlock();
        }
    }
}
