package me.kalpha.jpatest.tr.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @EntityListeners(AuditingEntityListener.class) 는 cteatedBy, lastModifiedBy를 위해 사용한다.
 */
@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass //DB컬럼을 상속할 수 있도록 한다.
public class BaseEntity extends CreatedBaseEntity {
    @LastModifiedBy
    private String lastModifiedBy;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
