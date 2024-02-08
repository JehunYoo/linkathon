export interface Audit {
    title:string,
    description:string,
    score:number,
}
export interface AuditCategory {
    score: number,
    description: string,
    manualDescription: string,
    audits:Audit[],
}

export interface AuditResult {
    [key: string]: AuditCategory,
}

export interface Report {
    url:string,
    report:AuditResult
}