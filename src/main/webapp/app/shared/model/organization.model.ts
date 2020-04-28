import { IRequest } from 'app/shared/model/request.model';
import { IIntern } from 'app/shared/model/intern.model';
import { IRequestAssignment } from 'app/shared/model/request-assignment.model';

export interface IOrganization {
  id?: number;
  employeeCount?: number;
  grossRevenue?: string;
  name?: string;
  taxNumber?: string;
  password?: string;
  email?: string;
  contact?: string;
  description?: string;
  address?: string;
  requests?: IRequest[];
  interns?: IIntern[];
  requestAssignments?: IRequestAssignment[];
}

export const defaultValue: Readonly<IOrganization> = {};
