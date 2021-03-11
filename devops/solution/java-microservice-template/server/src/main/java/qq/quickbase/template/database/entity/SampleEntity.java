package qq.quickbase.template.database.entity;


import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "entities")
public class SampleEntity {

    @Id
    private UUID id;

    @Column(unique = true)
    private String name;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder buildFrom(SampleEntity entity) {
        return new Builder()
                .withId(entity.getId())
                .withName(entity.getName());
    }

    // Generate using IDE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleEntity that = (SampleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    // Generate using IDE
    public static final class Builder {
        private UUID id;
        private String name;

        private Builder() {
        }

        public static Builder aSampleEntity() {
            return new Builder();
        }

        public Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public SampleEntity build() {
            SampleEntity sampleEntity = new SampleEntity();
            sampleEntity.id = this.id;
            sampleEntity.name = this.name;
            return sampleEntity;
        }
    }

}
