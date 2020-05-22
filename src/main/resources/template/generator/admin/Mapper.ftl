package ${package}.service.mapper;

import com.jump.commons.utils.mapper.EntityMapper;
import ${package}.domain.${className};
import ${package}.service.dto.${className}DTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author ${author}
* @date ${date}
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ${className}Mapper extends EntityMapper<${className}DTO, ${className}> {

}