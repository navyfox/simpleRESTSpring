package hello.items.repository;

import hello.items.model.Item;

import java.util.Map;

public interface ItemsRepository {

    Map<Long, String> getAllItems();

    Item create(Item newItem);

    Item getItemById(long id);

    Item update(long id, Item newItem);

    String delete(long id);
}
