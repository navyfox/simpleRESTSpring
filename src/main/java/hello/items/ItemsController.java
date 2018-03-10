package hello.items;

import hello.items.model.Item;
import hello.items.repository.ItemsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@Controller
@RequestMapping("/items")
public class ItemsController {

    private final ItemsRepository itemsRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection list() {
        return itemsRepository.getAllItems();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Item> create(@RequestBody Item newItem) {
        Item createdItem = itemsRepository.create(newItem);
        URI location = UriComponentsBuilder.fromPath("/items/")
                .path(String.valueOf(createdItem.getId()))
                .build().toUri();
        return ResponseEntity.created(location).body(createdItem);
    }

    public ItemsController(final ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }


}

