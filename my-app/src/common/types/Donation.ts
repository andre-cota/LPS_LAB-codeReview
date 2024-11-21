import { Professor } from "./Professor";
import { Student } from "./student";

export type Donation = {
    id: number;
    donationValue: number;
    date: Date;
    student: Student;
    professor: Professor;
}