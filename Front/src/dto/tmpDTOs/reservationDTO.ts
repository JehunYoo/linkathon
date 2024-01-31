export interface ReservationRequest {
    userId: number;
    reservationDateTime: Date;
}

export interface ScheduleRequest {
    times: Date[];
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