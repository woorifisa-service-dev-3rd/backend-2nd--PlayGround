package playground.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import playground.model.Fiance;
import playground.model.FianceType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FianceDTO {
    private Long id;

    private FianceType type;

    private String name;

    public static FianceDTO from(Fiance fiance){
        return FianceDTO.builder().id(fiance.getId()).type(fiance.getType()).name(fiance.getName()).build();
    }
}
