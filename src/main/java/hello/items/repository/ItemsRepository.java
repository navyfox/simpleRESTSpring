package hello.items.repository;

import hello.items.model.Item;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public interface ItemsRepository {

    Collection getAllItems();

    Item create(Item newItem);

    Item getItemById(long id);

    Item update(long id, Item newItem);

    boolean delete(long id);
}
