package hello.items.repository;

import hello.items.model.Item;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

public class DatabaseItemsRepository implements ItemsRepository {

    private final JdbcOperations jdbcOperations;

    public DatabaseItemsRepository(final JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Item> getAllItems() {
        return jdbcOperations.query(
                "SELECT id, name FROM item",
                (resultSet, i) -> {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    return new Item(id, name);
                });
    }


//    @Override
//    public Map<Long, String> getAllItems() {
//        return jdbcOperations.queryForMap("SELECT id, name FROM item").entrySet().stream()
//                .collect(Collectors.toMap(e -> Long.parseLong(e.getKey()), e -> (String) e.getValue()));
//    }

    @Override
    public Item create(final Item newItem) {
        long id = getNextId();      // or generate UUID
        String name = newItem.getName();
        int rows = jdbcOperations.update(
                "INSERT INTO item (id, name) VALUES (?, ?)",
                id, name
        );
        return new Item(id, name);
    }

    private long getNextId() {
        return jdbcOperations.queryForObject(
                "select nextval('item_id_seq')", Long.class);
    }

    @Override
    public Item getItemById(final long id) {
        return jdbcOperations.queryForObject(
                "SELECT id, name FROM item WHERE id = ?",
                (resultSet, i) -> {
                    long rowId = resultSet.getLong(1);
                    String rowName = resultSet.getString(2);
                    return new Item(rowId, rowName);
                },
                id);

    }

    @Override
    public Item update(final long id, final Item newItem) {
        String name = newItem.getName();
        int rows = jdbcOperations.update(
                "UPDATE item SET name = ? WHERE id = ?",
                name, id);
        return new Item(id, name);  // or select from DB
    }


    @Override
    public boolean delete(final long id) {
        int rows = jdbcOperations.update(
                "DELETE FROM item WHERE id = ?",
                id
        );
        return rows > 0;
    }

}
