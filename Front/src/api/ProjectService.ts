import {ApiService} from "@/api/ApiService.ts";
import {httpStatusCode} from "@/util/httpStatus.ts";
import {ProjectDetailDto, ProjectInfoDTO, ProjectRequestDto} from "@/dto/projectDTO.ts";
import {Builder} from "builder-pattern";
// import store from "@/store";
// import {CatchError} from "@/util/error.ts";

const apiService = new ApiService();

const url = "/api/projects"

class ProjectService {
    async getALlProjects(): Promise<ProjectInfoDTO[]> {
        try {
            const response = await apiService.getData(false, `${url}`, null);
            if (response && response.status === httpStatusCode.OK) {
                return response.data.content as ProjectInfoDTO[];
            }
        } catch (error) {
            console.error(error);
        }
        return [];
    }

    async getMyLikedProjects(): Promise<ProjectInfoDTO[]> {
        try {
            const response = await apiService.getData(false, `${url}/like`, null);
            if (response && response.status === httpStatusCode.OK) {
                return response.data as ProjectInfoDTO[];
            }
        } catch (error) {
            console.error(error);
        }
        return [];
    }

    async getPopularProjects(): Promise<ProjectInfoDTO[]> {
        try {
            const response = await apiService.getData(false, `${url}/popular`, null);
            if (response && response.status === httpStatusCode.OK) {
                return response.data as ProjectInfoDTO[];
            }
        } catch (error) {
            console.error(error);
        }
        return [];
    }

    async getMyProjects(): Promise<ProjectInfoDTO[]> {
        // try {
        //     const response = await apiService.getData(true, `${url}/projects`, null);
        //     if (response && response.status === httpStatusCode.OK) {
        //         alert("조회 성공");
        //         return response.data as ProjectInfoDTO[];
        //     }
        // } catch (error) {
        //     alert("조회 실패");
        // }
        return [];
    }

    async getProjectDetail(projectId: number): Promise<ProjectDetailDto> {
        try {
            const response = await apiService.getData(true, `${url}/${projectId}`, null);
            if (response && response.status === httpStatusCode.OK) {
                return response.data as ProjectDetailDto;
            }
        } catch (error) {
            console.error(error);
        }
        return Builder<ProjectDetailDto>()
            .build();
    }

    async registProject(projectRequestDto: ProjectRequestDto): Promise<void> {
        try {
            const response = await apiService.postData(true, `${url}`, projectRequestDto);
            if (response && response.status === httpStatusCode.OK) {
                return;
            }
        } catch (error) {
            console.error(error);
        }
    }

    async updateProject(projectId: number, projectRequestDto: ProjectRequestDto): Promise<void> {
        try {
            const response = await apiService.putData(true, `${url}/${projectId}`, projectRequestDto);
            if (response && response.status === httpStatusCode.OK) {
                return;
            }
        } catch (error) {
            console.error(error);
        }
    }

    async deleteProject(projectId: number): Promise<void> {
        try {
            const response = await apiService.deleteData(true, `${url}/${projectId}`, null);
            if (response && response.status === httpStatusCode.OK) {
                return;
            }
        } catch (error) {
            console.error(error);
        }
    }

    async submitProject(projectId: number): Promise<void> {
        try {
            const response = await apiService.postData(true, `${url}/${projectId}/submit`, null);
            if (response && response.status === httpStatusCode.OK) {
                return;
            }
        } catch (error) {
            console.error(error);
        }
    }

    async likeProject(projectId: number): Promise<void> {
        try {
            const response = await apiService.postData(true, `${url}/${projectId}/like`, null);
            if (response && response.status === httpStatusCode.OK) {
                return;
            }
        } catch (error) {
            console.error(error);
        }
    }

    async unlikeProject(projectId: number): Promise<void> {
        try {
            const response = await apiService.deleteData(true, `${url}/${projectId}/like`, null);
            if (response && response.status === httpStatusCode.OK) {
                return;
            }
        } catch (error) {
            console.error(error);
        }
    }
}

class ProjectServiceTest {
    async testAll() {
        const projectService: ProjectService = new ProjectService();

        projectService.getALlProjects().then(d => console.log(d));
        projectService.getMyLikedProjects().then(d => console.log(d));
        projectService.getPopularProjects().then(d => console.log(d));
        projectService.getMyProjects().then(d => console.log(d));

        projectService.getProjectDetail(2).then(d => console.log(d));
        // projectService.deleteProject(1).then(d => console.log(d));
        // projectService.registProject(Builder<ProjectRequestDto>()
        //     .projectName("asd")
        //     .projectDesc("프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로")
        //     .projectUrl("asd")
        //     .deployUrl("asd")
        //     .teamId(1)
        //     .build()).then(d => console.log(d));

        // projectService.updateProject(2, Builder<ProjectRequestDto>()
        //     .projectName("asd")
        //     .projectDesc("프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로")
        //     .projectUrl("asd")
        //     .deployUrl("asd")
        //     .teamId(2)
        //     .build()).then(d => console.log(d));

        // projectService.submitProject(2).then(d => console.log(d));
        projectService.likeProject(4).then(d => console.log(d));
        projectService.unlikeProject(4).then(d => console.log(d));
    }
}

export {
    ProjectService, ProjectServiceTest
}