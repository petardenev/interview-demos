package qq.quickbase.template.converters;

import qq.quickbase.template.database.entity.SampleEntity;
import qq.quickbase.template.server.api.model.Entity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ApiEntityToEntityConverter implements Converter<Entity, SampleEntity> {

    @Override
    public SampleEntity convert(Entity entity) {

        return SampleEntity.builder()
                .withId(Optional.ofNullable(entity.getId()).map(UUID::fromString).orElse(null))
                .withName(entity.getName())
                .build();
    }

}
