import { Moment } from 'moment';
import { IRegisterRequest } from 'app/shared/model/register-request.model';
import { IRequestAssignment } from 'app/shared/model/request-assignment.model';
import { IAbility } from 'app/shared/model/ability.model';

export interface IIntern {
  id?: number;
  code?: number;
  firstName?: string;
  lastName?: string;
  dateOfBirth?: Moment;
  joinDate?: Moment;
  className?: string;
  avatar?: string;
  password?: string;
  email?: string;
  phone?: number;
  description?: string;
  address?: string;
  registerRequests?: IRegisterRequest[];
  requestAssignments?: IRequestAssignment[];
  internAbilities?: IAbility[];
  organizationInternId?: number;
}

export const defaultValue: Readonly<IIntern> = {};
