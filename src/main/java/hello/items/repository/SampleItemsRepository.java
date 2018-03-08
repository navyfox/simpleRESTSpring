package hello.items.repository;

import hello.items.model.Item;

import java.util.*;

public class SampleItemsRepository implements ItemsRepository {

    private Map<Long, String> items = new HashMap<>();

    public SampleItemsRepository() {
        items.put((long) 1, "one");
        items.put((long) 2, "two");
        items.put((long) 3, "three");
    }
    @Override
    public Map<Long, String> getAllItems() {
        return Collections.unmodifiableMap(items);
    }

    @Override
    public Item create(final Item newItem) {
        items.put(newItem.getId(), newItem.getName());
        return newItem;
    }

    @Override
    public Item getItemById(final long id) {
        return new Item(id, items.get(id));

    }

    @Override
    public String update(final long id, final Item newItem) {
        return items.put(id, newItem.getName());
    }

    @Override
    public String delete(final long id) {
        return items.remove(id);
    }
}

