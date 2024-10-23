import { Address } from './address';
import { NaturalPerson } from './naturalPerson';
export type Student = {
    address: Address;
    rg:String;
    cpf:String;
    balance:number;
    courseId:Number;
} & NaturalPerson;