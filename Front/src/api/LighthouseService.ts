import {ApiService} from "@/api/ApiService.ts";
import {CatchError} from "@/util/error.ts";
import {AuditResult, Report} from "@/Interface/frontReport.ts";

const apiService = new ApiService();

const url = "/api/lighthouse"

export class LighthouseService {

    @CatchError
    async getLighthouseReport(projectId: number): Promise<Report[]> {
        const response = await apiService.getData(false, `${url}/report/${projectId}`);

        interface TempReport {
            url: string,
            report: string,
        }

        return response.data.map((item: TempReport) => {
            return {
                url: item.url,
                report: JSON.parse(item.report) as AuditResult,
            };
        });
    }

    @CatchError
    async updateLighthouseReport(projectId: number) {
        await apiService.postData(true, `${url}/report/${projectId}`, '');
    }
}