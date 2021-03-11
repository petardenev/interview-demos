package qq.quickbase.template.database.repository;

import qq.quickbase.template.database.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SampleEntityRepository extends JpaRepository<SampleEntity, UUID> {

    SampleEntity getByName(String name);

}
