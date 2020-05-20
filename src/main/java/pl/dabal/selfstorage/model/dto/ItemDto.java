package pl.dabal.selfstorage.model.dto;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;
import pl.dabal.selfstorage.model.Category;
import pl.dabal.selfstorage.model.Metric;
import pl.dabal.selfstorage.model.Storage;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class ItemDto {

    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @DecimalMin("0.0")
    private Double quantity;

    private Category category;

    private Metric metric;

    private Storage storage;


}
