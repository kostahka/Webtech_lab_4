package by.bsuir.lab4.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class DatabaseQuery {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static DatabaseQuery INSTANCE;
    private ThreadPoolExecutor executor;
    private DatabaseQuery(){
        try {
            Class<? extends DatabaseQuery> aClass = this.getClass();
            ClassLoader classLoader = aClass.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("db.properties");

            Properties property = new Properties();
            property.load(inputStream);

            Integer connectionSize = Integer.parseInt(property.getProperty("db.connectionSize"));
            executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(connectionSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static DatabaseQuery getInstance(){
        if (!initialized.get()) {
            try {
                lock.lock();
                if (!initialized.get()) {
                    INSTANCE = new DatabaseQuery();
                    initialized.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return INSTANCE;
    }
    public ResultSet PutExecuteQuery(String sql, List<Object> params){
        Future<ResultSet> result = executor.submit(()->{
            Connection connection = null;
            try {
                connection = DatabaseConnectionManager.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                QueryPrepare.prepare(preparedStatement, params);
                return preparedStatement.executeQuery();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }finally {
                if(connection != null)
                    DatabaseConnectionManager.getInstance().returnConnection(connection);
            }
        });
        try {
            return result.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void PutExecuteUpdate(String sql, List<Object> params){
        executor.submit(()->{
            Connection connection = null;
            try {
                connection = DatabaseConnectionManager.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                QueryPrepare.prepare(preparedStatement, params);
                return preparedStatement.executeUpdate();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }finally {
                if(connection != null)
                    DatabaseConnectionManager.getInstance().returnConnection(connection);
            }
        });
    }
}
