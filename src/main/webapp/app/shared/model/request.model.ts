import { Moment } from 'moment';
import { IRegisterRequest } from 'app/shared/model/register-request.model';
import { IAbility } from 'app/shared/model/ability.model';

export interface IRequest {
  id?: number;
  position?: string;
  amount?: number;
  dateCreated?: Moment;
  status?: number;
  description?: string;
  type?: string;
  registerRequests?: IRegisterRequest[];
  requestAbilities?: IAbility[];
  organizationRequestName?: string;
  organizationRequestId?: number;
  requestStatusName?: string;
  requestStatusId?: number;
}

export const defaultValue: Readonly<IRequest> = {};
