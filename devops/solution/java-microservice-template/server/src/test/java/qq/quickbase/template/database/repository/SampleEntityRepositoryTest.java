package qq.quickbase.template.database.repository;

import qq.quickbase.template.database.entity.SampleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SampleEntityRepositoryTest {

    @Autowired
    private SampleEntityRepository entityRepository;

    @Test
    @Transactional
    public void shouldGetEntityById() {
        // when
        var entity = entityRepository.getOne(UUID.fromString("efd8fee0-3ae5-4376-9f08-24274134a34b"));

        // then
        assertEquals("efd8fee0-3ae5-4376-9f08-24274134a34b", entity.getId().toString());
        assertEquals("test-entity", entity.getName());
    }

    @Test
    @Transactional
    public void shouldCreateEntity() {
        // when
        var entity = SampleEntity.builder()
                .withId(UUID.randomUUID())
                .withName("Some other test").build();
        entityRepository.save(entity);

        // then
        var entityFromDB = entityRepository.getOne(entity.getId());

        // then
        assertEquals(entityFromDB.getId(), entity.getId());
        assertEquals(entityFromDB.getName(), entity.getName());
    }
}
