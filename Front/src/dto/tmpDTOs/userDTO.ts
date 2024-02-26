import {Field} from "@/dto/tmpDTOs/commonDTO.ts";
import {SkillResponseDto} from "@/dto/tmpDTOs/skillDTO.ts";

export interface LoginRequest {
    email: string;
    password: string;
}

export interface UserInfoRequest {
    email: string;
    password: string;
    name: string;
    nickname: string;
    birth: Date;
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

export interface UserSearchConditionDto {
    skillIds: number[];
    tier: number;
    career: number;
    gender: boolean;
    field: Field;
}

export interface CandidateResponseDto {
    userId: number;
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