package qq.quickbase.template.service;

import qq.quickbase.template.database.entity.SampleEntity;
import qq.quickbase.template.database.repository.SampleEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SampleEntityService {

    @Autowired
    private SampleEntityRepository entityRepository;

    public Optional<SampleEntity> getById(UUID id) {
        try {
            return Optional.of(entityRepository.getOne(id));
        } catch (EntityNotFoundException ex) {
            return Optional.empty();
        }
    }

    public List<SampleEntity> getAll() {
        return entityRepository.findAll();
    }

    public SampleEntity create(SampleEntity entity) {
        SampleEntity toPersist = SampleEntity.builder()
                .withId(UUID.randomUUID())
                .withName(entity.getName())
                .build();
        return entityRepository.save(toPersist);
    }

    public void update(SampleEntity existingEntity) {
        entityRepository.save(existingEntity);
    }

    public void delete(UUID id) {
        entityRepository.deleteById(id);
    }

}
