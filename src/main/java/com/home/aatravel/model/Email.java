package com.home.aatravel.model;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    private String username;

    private String domainName;

    @Override
    public String toString() {
        return String.format("%s@%s", username, domainName);
    }
}
