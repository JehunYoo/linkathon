/* tslint:disable */
/* eslint-disable */

// Generated using typescript-generator version 3.2.1263 on 2024-01-31 14:47:32.

export interface Image {
    imageName: string;
    imageUrl: string;
    originName: string;
}

export interface JwtToken {
    grantType: string;
    accessToken: string;
    refreshToken: string;
}

export interface LoginRequest {
    email: string;
    password: string;
}

export interface ProjectImageDto {
    projectImgName: string;
    projectImgUrl: string;
    projectOriginImgName: string;
}

export interface RefreshToken {
    id: number;
    refreshToken: string;
}

export interface UserInfoRequest {
    email: string;
    password: string;
    name: string;
    nickname: string;
    birth: Date;
}

export interface UserInfoRequestBuilder {
}

export interface UserPasswordResetRequest {
    email: string;
    password: string;
}


export interface UserSignUpDto {
    email: string;
    password: string;
    name: string;
    gender: boolean;
    birth: Date;
    phoneNumber: string;
    rating: number;
}


export interface CreateTeamRequestDto {
    teamName: string;
    teamDesc: string;
    teamSkills: number[];
}

export interface HackathonRequest {
    hackathonName: string;
    teamDeadlineDate: Date;
    startDate: Date;
    endDate: Date;
    maxPoint: number;
    max_team_member: number;
}


export interface PermissionToRemoveMemberRequestDto {
    uuid: string;
}

export interface ProjectRequestDto {
    teamId: number;
    projectName: string;
    projectDesc: string;
    projectUrl: string;
    deployUrl: string;
    projectImage: ProjectImageDto;
}


export interface ReservationRequest {
    userId: number;
    reservationDateTime: Date;
}


export interface ScheduleRequest {
    times: Date[];
}


export interface TeamImageRequestDto {
}

export interface UpdateTeamRequestDto {
    teamName: string;
    teamDesc: string;
    teamSkillSets: TeamSkill[];
}

export interface UserSearchConditionDto {
    skillIds: number[];
    tier: number;
    career: number;
    gender: boolean;
    field: Field;
}

export interface BackPerformanceResponseDto {
    backPerformanceId: number;
    bugs: number;
    codeSmells: number;
    coverage: number;
    duplications: number;
    securityRating: number;
    vulnerabilities: number;
}


export interface CandidateResponseDto {
    name: string;
    rating: number;
    introduce: string;
    userImageUrl: string;
    userOriginImageName: string;
    skillSets: SkillResponseDto[];
}

export interface CandidatesResponseDto {
    candidates: CandidateResponseDto[];
}

export interface HackathonInfoResponseDto {
    hackathonName: string;
    registerDate: Date;
    teamDeadlineDate: Date;
    startDate: Date;
    endDate: Date;
    hackathonImageName: string;
    hackathonOriginImageName: string;
}

export interface HackathonResponseDto {
    hackathonName: string;
    registerDate: Date;
    teamDeadlineDate: Date;
    startDate: Date;
    endDate: Date;
    maxPoint: number;
    hackathonImageName: string;
    hackathonImageUrl: string;
    hackathonOriginImageName: string;
}


export interface HackathonsResponseDto {
    hackathonId: number;
    hackathonName: string;
    registerDate: Date;
    teamDeadlineDate: Date;
    startDate: Date;
    endDate: Date;
    maxPoint: number;
    hackathonImageName: string;
    hackathonImageUrl: string;
    hackathonOriginImageName: string;
}


export interface MemberDetailResponseDto {
    userId: number;
    name: string;
    introduce: string;
    rating: number;
    career: number;
    referenceUrl: string;
    field: Field;
    profileOriginImageName: string;
    profileImageURL: string;
    skillSets: SkillDto[];
}

export interface MemberResponseDto {
    name: string;
    role: Role;
    rating: number;
    introduce: string;
    userImageUrl: string;
    userOriginImageName: string;
    skillSets: SkillResponseDto[];
}

export interface MembersResponseDto {
    members: MemberResponseDto[];
}

export interface ProjectDetailResponseDto {
    projectId: number;
    teamId: number;
    hackathonId: number;
    hackathonName: string;
    hackathonTopic: string;
    hackathonScore: number;
    projectName: string;
    projectDesc: string;
    projectStatus: ProjectStatus;
    registeredDate: Date;
    projectUrl: string;
    deployUrl: string;
    winState: boolean;
    projectImage: ProjectImageDto;
    starCount: number;
    starred: boolean;
}


export interface ProjectResponseDto {
    projectId: number;
    projectName: string;
    projectDesc: string;
    starCount: number;
    starred: boolean;
    image: Image;
}


export interface ReservationResponse {
    reservationId: number;
    userId: number;
    isLeader: boolean;
    reservationDatetime: Date;
}


export interface ScheduleResponse {
    userId: number;
    times: Date[];
}


export interface SkillDto {
    skillName: string;
    skillImageUrl: string;
    skillLevel: number;
}

export interface SkillResponseDto {
    skillName: string;
    skillImageUrl: string;
    skillLevel: string;
    skillType: string;
}

export interface TeamApplicationResponseDto {
    teamName: string;
    teamDesc: string;
    hackathonInfoResponseDto: HackathonInfoResponseDto;
    members: TeamMemberResponseDto[];
    teams: TeamListSimpleResponseDto[];
}

export interface TeamIdsResponseDto {
    teamIds: number[];
}

export interface TeamListSimpleResponseDto {
    teamId: number;
    teamName: string;
}

export interface TeamMemberResponseDto {
    userId: number;
    name: string;
    rating: number;
    introduce: string;
    userImageUrl: string;
    userOriginImageName: string;
    skillSets: SkillResponseDto[];
}

export interface TeamResponseDto {
    teamName: string;
    teamMember: number;
    teamDesc: string;
    teamSkills: TeamSkillDto[];
    members: TeamMemberResponseDto[];
}

export interface TeamSkillDto {
    skillName: string;
    skillImageUrl: string;
}

export interface WinnerProjectInfoDto {
    projectId: number;
    teamId: number;
    projectName: string;
    projectDesc: string;
}


export interface WinnerProjectResponseDto {
    winnerProjectInfoDto: WinnerProjectInfoDto;
    teamResponseDto: TeamResponseDto;
}


export interface GrantedAuthority extends Serializable {
    authority: string;
}

export interface UserDetails extends Serializable {
    enabled: boolean;
    password: string;
    username: string;
    authorities: GrantedAuthority[];
    accountNonExpired: boolean;
    credentialsNonExpired: boolean;
    accountNonLocked: boolean;
}

export interface TeamSkill {
    teamSkillId: number;
    team: Team;
    skill: Skill;
}

export interface Serializable {
}

export interface Team {
    teamId: number;
    hackathon: Hackathon;
    userTeamList: UserTeam[];
    teamName: string;
    teamStatus: TeamStatus;
    teamMember: number;
    teamDesc: string;
    teamSkills: TeamSkill[];
    teamImage: TeamImage;
}

export interface Skill {
    skillId: number;
    skillName: string;
    skillImageUrl: string;
    skillType: SkillType;
}

export interface Hackathon {
    hackathonId: number;
    hackathonName: string;
    hackathonImage: HackathonImage;
    registerDate: Date;
    teamDeadlineDate: Date;
    startDate: Date;
    endDate: Date;
    maxPoint: number;
    maxTeamMember: number;
}

export interface UserTeam {
    userTeamId: number;
    user: User;
    team: Team;
    role: Role;
    memberStatus: MemberStatus;
}

export interface TeamImage {
    teamImageId: number;
    teamImageName: string;
    teamImageUrl: string;
    teamOriginImageName: string;
}

export interface HackathonImage {
    hackathonImageId: number;
    hackathonImageName: string;
    hackathonImageUrl: string;
    hackathonOriginImageName: string;
}

export interface User extends UserDetails {
    userId: number;
    userImage: UserImage;
    email: string;
    phoneNumber: string;
    name: string;
    gender: boolean;
    birth: Date;
    rating: number;
    registered: boolean;
    registeredDate: Date;
    career: number;
    referenceUrl: string;
    deployUrl: string;
    introduce: string;
    field: Field;
    joinState: boolean;
    userSkills: UserSkill[];
}

export interface UserImage {
    userImageId: number;
    userImageName: string;
    userImageUrl: string;
    userOriginImageName: string;
}

export interface UserSkill {
    userSkillId: number;
    skillLevel: number;
    user: User;
    skill: Skill;
}

export type SocialType = "GOOGLE" | "GITHUB";

export type Field = "FRONTEND" | "BACKEND" | "FULLSTACK" | "DESIGN" | "MANAGE";

export type Role = "LEADER" | "MEMBER" | "CANDIDATE";

export type ProjectStatus = "OPENED" | "CLOSED";

export type TeamStatus = "BUILDING" | "COMPLETE";

export type SkillType =
    "LANGUAGE"
    | "FRONTEND"
    | "BACKEND"
    | "TESTING"
    | "DATABASE"
    | "DATA"
    | "DEVOPS"
    | "TOOL"
    | "DESIGN";

export type MemberStatus = "JOINED" | "SUGGESTED" | "APPLIED";
