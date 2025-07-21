package com.ems.TasksManagementSystem.mapper;


import com.ems.TasksManagementSystem.dto.ReportProjectTaskDto;
import com.ems.TasksManagementSystem.entity.ProjectTaskReport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ReportProjectTaskMapper {

    ReportProjectTaskDto entityToDto(ProjectTaskReport report);

    List<ReportProjectTaskDto> entityToDto(List<ProjectTaskReport> reports);

    ProjectTaskReport DtoToEntity(ReportProjectTaskDto report);

    List<ProjectTaskReport> DtoToEntity(List<ReportProjectTaskDto> reports);


}
