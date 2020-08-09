package jeremic.petar.fon.springgames.repository;

import jeremic.petar.fon.springgames.entity.Creature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/*
NOTE: When comparing concrete values, they are expected because they are or are not inserted in database.
Used plugin: Liquibase
    (see /test/resources/db/changelog/test-changelog_db_generated_data.xml )
 */



@DataJpaTest
class CreatureRepositoryTest {

    @Autowired
    private CreatureRepository creatureRepository;

    private Creature creatureForInsert;

    @BeforeEach
    void setUp() {
        creatureForInsert = new Creature();
        creatureForInsert.setName("Avatar");
        creatureForInsert.setDietType("omnivore");
    }

    @Test
    void findById() {
        Creature foundCreature = creatureRepository.findById(1L).get();
        assertNotNull(foundCreature);
        assertEquals(1L, foundCreature.getId());
    }

    @Test
    void findByIdException() {
        assertThrows(NoSuchElementException.class, () -> creatureRepository.findById(-1L).get());
    }

    @Test
    void save() {
        Creature savedCreature = creatureRepository.save(creatureForInsert);

        assertNotNull(savedCreature);
        assertEquals(savedCreature.getName(), creatureForInsert.getName());
        assertEquals(savedCreature.getDietType(), creatureForInsert.getDietType());
    }
}