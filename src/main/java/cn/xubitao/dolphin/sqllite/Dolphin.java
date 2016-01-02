package cn.xubitao.dolphin.sqllite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class Dolphin {
    @Value("${sqllite.dbpath}")
    private String dbPath;
    public  synchronized <D extends Dao<T, ?>, T> D lite(Class<T> clazz) {
        try {
            ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite:" + dbPath);
            return DaoManager.createDao(connectionSource, clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
