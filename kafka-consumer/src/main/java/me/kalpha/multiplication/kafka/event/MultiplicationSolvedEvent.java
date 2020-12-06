package me.kalpha.multiplication.kafka.event;

import lombok.*;

import java.io.Serializable;

/**
 * 곱셈에 대한 컨텍스트 정보를 제공.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MultiplicationSolvedEvent implements Serializable {

  private Long multiplicationResultAttemptId;
  private Long userId;
  private boolean correct;
}
