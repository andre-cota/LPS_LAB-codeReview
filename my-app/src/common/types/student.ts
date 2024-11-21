import { Address } from './address';
import { Course } from './Course';
import { NaturalPerson } from './naturalPerson';

export type Student = {
    id: number,
    name: string,
    email: string,
    cpf: string,
    balance: number,
    rg: string,
    course: Course,
    address: Address
} & NaturalPerson;