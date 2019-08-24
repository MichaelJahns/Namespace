package michaelj.namespace.namespace.courier;

import com.sun.javafx.beans.IDProperty;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication{
    public static void main(String[] args) { SpringApplication.run(DemoApplication.class, args); }

}
@Entity
class Character{
    @Id
    @GeneratedValue
    private long id;
    private String name;

    public Character(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

interface CharacterRepository extends JpaRepository<Character, Long>{

}

@Component
class CharacterCommandLineRunner implements CommandLineRunner {
    private final CharacterRepository repo;

    public CharacterCommandLineRunner(CharacterRepository repo){
        this.repo = repo;
    }
    @Override
    public void run(String... strings) throws Exception {
        Stream.of("Faltrass", "Marl", "Kaladin", "Thram", "Pid Padfoot")
                .forEach(name -> repo.save(new Character(name)) );
        repo.findAll().forEach(System.out::println);
    }

}