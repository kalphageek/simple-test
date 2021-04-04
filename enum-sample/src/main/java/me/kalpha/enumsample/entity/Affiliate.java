package me.kalpha.enumsample.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Affiliate {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private String representative;

    @Enumerated(EnumType.STRING)
    private Code code;

    @Getter
    @AllArgsConstructor
    public enum Code {
        SKT("SK텔레콤", "w01", "s001"),
        SKCC("SK C&C", "w02", "s015"),
        ST11("11번가", "w03", "s020");

        private String viewName;
        private String companyCode;
        private String bizType;
    }
}
