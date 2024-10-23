import { User } from "./user";

export type NaturalPerson = {
    cpf: string;
    balance: number;
} & User;