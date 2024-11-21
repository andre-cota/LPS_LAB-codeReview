import { Department } from "./Department";
import { NaturalPerson } from "./naturalPerson";

export type Professor = {
    id: number;
    name: string;
    email: string;
    cpf: string;
    balance: number;
    department: Department;
} & NaturalPerson