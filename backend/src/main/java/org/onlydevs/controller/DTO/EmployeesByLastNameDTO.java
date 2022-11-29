package org.onlydevs.controller.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeesByLastNameDTO {
    List<EmployeeDTO> employeeDTOList;
}
