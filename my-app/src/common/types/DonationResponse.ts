import { Professor } from "./Professor";
import { Student } from "./student";

export type DonationResponse = {
    id: number;
    donationValue: number;
    date: [number, number, number, number, number, number]; // [year, month, day, hour, minute, second]
    student: Student;
    professor: Professor;
};
