import {ApiService} from "@/api/ApiService.ts";
import {httpStatusCode} from "@/util/httpStatus.ts";
import {PageableProjects, ProjectDetailDto, ProjectInfoDTO, ProjectRequestDto} from "@/dto/projectDTO.ts";
import {Builder} from "builder-pattern";
import {AxiosResponse} from "axios";
import projectStorage from "@/store/projectStorage.ts";
// import store from "@/store";
// import {CatchError} from "@/util/error.ts";

const apiService = new ApiService();

const url = "/api/projects"

interface pageableData {
    content: Object,
    pageable: {
        pageNumber: number,
    }
    totalPages: number,
}

class ProjectService {

    toPageableProjects = (response: AxiosResponse<any, any>): PageableProjects => {
        const data = response.data as pageableData;
        return Builder<PageableProjects>()
            .projects(data.content as ProjectInfoDTO[])
            .pageable(Builder<PageableDto>()
                .pageNumber(data.pageable.pageNumber)
                .totalPages(data.totalPages)
                .build())
            .build();
    }

    async starClick (data: ProjectInfoDTO, projectId: number)  {
        if (data.starred) {
            await projectStorage.getters.getProjectService.unlikeProject(projectId);
            data.starCount--;
            data.starred = false;
        } else {
            await projectStorage.getters.getProjectService.likeProject(projectId);
            data.starCount++;
            data.starred = true;
        }
    }

    // 전송 관련 메서드들

    async getALlProjects(pageNumber: number | undefined, pageSize: number | undefined): Promise<PageableProjects> {
        try {
            const response = await apiService.getData(true, `${url}`,
                {params: {
                        page: pageNumber,
                        size: pageSize
                    }});
            if (response && response.status === httpStatusCode.OK) {
                return this.toPageableProjects(response);
            }
        } catch (error) {
            console.error(error);
        }
        return {} as PageableProjects;
    }

    async getMyLikedProjects(): Promise<PageableProjects> {
        try {
            const response = await apiService.getData(true, `${url}/like`, null);
            if (response && response.status === httpStatusCode.OK) {
                return this.toPageableProjects(response);
            }
        } catch (error) {
            console.error(error);
        }
        return {} as PageableProjects;
    }

    async getPopularProjects(pageNumber: number | undefined, pageSize: number | undefined): Promise<PageableProjects> {
        try {
            const response = await apiService.getData(true, `${url}/popular`, {params: {
                    page: pageNumber,
                    size: pageSize
                }});
            if (response && response.status === httpStatusCode.OK) {
                return this.toPageableProjects(response);
            }
        } catch (error) {
            console.error(error);
        }
        return {} as PageableProjects;
    }

    async getMyProjects(): Promise<PageableProjects> {
        try {
            const response = await apiService.getData(true, `${url}/my-project`, null);
            if (response && response.status === httpStatusCode.OK) {
                return this.toPageableProjects(response);
            }
        } catch (error) {
            console.error(error);
        }
        return {} as PageableProjects;
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
        return {} as ProjectDetailDto;

    }

    // async registProject(projectRequestDto: ProjectRequestDto): Promise<void> {
    //     try {
    //         const response = await apiService.postData(true, `${url}`, projectRequestDto);
    //         if (response && response.status === httpStatusCode.OK) {
    //             return;
    //         }
    //     } catch (error) {
    //         console.error(error);
    //     }
    // }

    async updateProject(projectId: number, projectRequestDto: ProjectRequestDto, image: File | null): Promise<void> {
        try {
            const data: FormData = new FormData();
            data.append('project', new Blob([JSON.stringify(projectRequestDto as any)], { type: "application/json" }));
            data.append('image', image as any);

            const response = await apiService.postMultipartData(true, `${url}/${projectId}`, data);
            if (response && response.status === httpStatusCode.OK) {
                return;
            }
        } catch (error) {
            console.error(error);
            return Promise.reject();
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
    // async testAll() {
    //     const projectService: ProjectService = new ProjectService();
    //
    //     projectService.getALlProjects().then(d => console.log(d));
    //     projectService.getMyLikedProjects().then(d => console.log(d));
    //     projectService.getPopularProjects().then(d => console.log(d));
    //     projectService.getMyProjects().then(d => console.log(d));
    //
    //     projectService.getProjectDetail(2).then(d => console.log(d));
    //     // projectService.deleteProject(1).then(d => console.log(d));
    //     // projectService.registProject(Builder<ProjectRequestDto>()
    //     //     .projectName("asd")
    //     //     .projectDesc("프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로")
    //     //     .projectUrl("asd")
    //     //     .deployUrl("asd")
    //     //     .teamId(1)
    //     //     .build()).then(d => console.log(d));
    //
    //     // projectService.updateProject(2, Builder<ProjectRequestDto>()
    //     //     .projectName("asd")
    //     //     .projectDesc("프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로젝트 설명프로")
    //     //     .projectUrl("asd")
    //     //     .deployUrl("asd")
    //     //     .teamId(2)
    //     //     .build()).then(d => console.log(d));
    //
    //     // projectService.submitProject(2).then(d => console.log(d));
    //     projectService.likeProject(4).then(d => console.log(d));
    //     projectService.unlikeProject(4).then(d => console.log(d));
    // }
}

export {
    ProjectService, ProjectServiceTest
}