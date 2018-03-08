package hello.items;

import hello.items.model.Item;
import hello.items.repository.ItemsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("/items/{id}")
public class ItemsIDController {

    private final ItemsRepository itemsRepository;

    public ItemsIDController(final ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Item> get(@PathVariable long id) {
        Item result = itemsRepository.getItemById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Item> update(@PathVariable long id, @RequestBody Item newItem) {
        Item result = itemsRepository.update(id, newItem);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI location = UriComponentsBuilder.fromPath("/items/")
                    .path(String.valueOf(result.getId()))
                    .build().toUri();
            return ResponseEntity.created(location).body(result);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@PathVariable long id) {
        String deleted = itemsRepository.delete(id);
        if (deleted != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
