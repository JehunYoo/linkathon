export interface Image {
    imageName: string;
    imageUrl: string;
    originName: string;
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