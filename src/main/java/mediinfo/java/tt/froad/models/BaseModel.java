package mediinfo.java.tt.froad.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "is_deleted", nullable = false)
    private Short isDeleted = 0;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt = OffsetDateTime.now();

    @Column(name = "deleted_by")
    private Long deletedBy;

    public void softDelete() {
        this.isDeleted = 1;
    }
}
