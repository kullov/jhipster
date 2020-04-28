import { Moment } from 'moment';

export interface IRegisterRequest {
  id?: number;
  dateCreated?: Moment;
  startDate?: Moment;
  endDate?: Moment;
  internRegisterCode?: string;
  internRegisterId?: number;
  requestRegisterPosition?: string;
  requestRegisterId?: number;
}

export const defaultValue: Readonly<IRegisterRequest> = {};
