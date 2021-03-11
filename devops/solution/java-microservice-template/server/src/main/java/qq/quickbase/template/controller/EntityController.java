package qq.quickbase.template.controller;

import qq.quickbase.template.database.entity.SampleEntity;
import qq.quickbase.template.server.api.EntitiesApi;
import qq.quickbase.template.server.api.model.Entity;
import qq.quickbase.template.service.SampleEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Transactional
public class EntityController implements EntitiesApi {

    @Autowired
    private SampleEntityService entityService;

    @Autowired
    private ConversionService conversionService;

    @Override
    public ResponseEntity<Entity> createEntity(@Valid Entity entity) {
        var newEntity = conversionService.convert(entity, SampleEntity.class);
        assert newEntity != null;
        var persistedEntity = entityService.create(newEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(conversionService.convert(persistedEntity, Entity.class));
    }

    @Override
    public ResponseEntity<Void> deleteEntity(String entityId) {
        entityService.delete(UUID.fromString(entityId));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Entity> getEntity(String entityId) {
        return entityService.getById(UUID.fromString(entityId))
                .map(entity -> conversionService.convert(entity, Entity.class))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<List<Entity>> getentities() {
        // TODO Use a wrapper response in API to support total count and pagination in future
        var response = entityService.getAll().stream()
                .map(entity -> conversionService.convert(entity, Entity.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> updateEntity(String entityId, @Valid Entity entity) {
        entityService.update(conversionService.convert(entity, SampleEntity.class));
        return ResponseEntity.noContent().build();
    }

}
