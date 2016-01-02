package cn.xubitao.dolphin.sqllite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by xubitao on 12/25/15.
 */
@DatabaseTable(tableName = "test_table")
public class TestTable {
    // 主键 id 自增长
    @DatabaseField(generatedId = true)
    private Integer id;
    // 映射
    @DatabaseField(canBeNull = false)
    private String name;
    // 不为空
    @DatabaseField(canBeNull = false)
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
