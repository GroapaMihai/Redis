package com.bitwise.springbootcaching.models.dtos;

import com.bitwise.springbootcaching.models.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CreateCategoryDto {
    @NotBlank(message = "This field is required")
    private String name;

    public Category toCategory() {
        Category category = new Category();

        category.setName(this.name);

        return category;
    }
}
