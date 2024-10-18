import { Address } from './address';
import { User } from './user';
export type Student = {
    address: Address;
    rg:String;
    course:String;
} & User;