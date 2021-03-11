package qq.quickbase.template.converters;

import qq.quickbase.template.database.entity.SampleEntity;
import qq.quickbase.template.server.api.model.Entity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class EntityToApiEntityConverter implements Converter<SampleEntity, Entity> {

    @Override
    public Entity convert(SampleEntity entity) {
        return new Entity()
                .id(Optional.ofNullable(entity.getId()).map(UUID::toString).orElse(null))
                .name(entity.getName());
    }
}
