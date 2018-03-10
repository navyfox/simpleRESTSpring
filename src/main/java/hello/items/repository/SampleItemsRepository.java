package hello.items.repository;

import hello.items.model.Item;

import java.util.*;

public class SampleItemsRepository implements ItemsRepository {

    private Map<Long, Item> items = new HashMap<>();

    public SampleItemsRepository() {
        items.put((long) 1, new Item(1, "one"));
        items.put((long) 2, new Item(2,"two"));
        items.put((long) 3, new Item(3,"three"));
    }
    @Override
    public Collection<Item> getAllItems() {
        return Collections.unmodifiableCollection(items.values());
    }

    @Override
    public Item create(final Item newItem) {
        items.put(newItem.getId(), newItem);
        return newItem;
    }

    @Override
    public Item getItemById(final long id) {
        if (items.containsKey(id)) {
            return items.get(id);
        }
        return null;

    }

    @Override
    public Item update(final long id, final Item newItem) {
        if (items.containsKey(id)) {
            items.put(id, newItem);
            return newItem;
        }
        return null;
    }

    @Override
    public boolean delete(final long id) {
        if (items.containsKey(id)) {
            items.remove(id);
            return true;
        }
        return false;
    }
}

