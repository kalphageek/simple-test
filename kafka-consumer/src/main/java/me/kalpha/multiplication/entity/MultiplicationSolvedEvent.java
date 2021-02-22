package me.kalpha.multiplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 곱셈에 대한 컨텍스트 정보를 제공.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "multiplication_solved_event")
public class MultiplicationSolvedEvent implements Serializable {
  @Id
  @Column(name = "multiplication_result_attempt_id")
  private Long multiplicationResultAttemptId;
  @Column(name = "user_id")
  private Long userId;
  private boolean correct;
}
