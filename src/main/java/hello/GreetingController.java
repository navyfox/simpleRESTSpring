package hello;

import java.net.URI;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Greeting> create(@RequestBody  long id, String content) {
        Greeting createdItem = new Greeting(id, content);
        URI location = UriComponentsBuilder.fromPath("/items/").path(String.valueOf(createdItem.getId())).build().toUri();
        return ResponseEntity.created(location).body(createdItem);
    }

}
