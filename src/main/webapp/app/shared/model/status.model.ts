import { IRequest } from 'app/shared/model/request.model';
import { IRequestAssignment } from 'app/shared/model/request-assignment.model';

export interface IStatus {
  id?: number;
  name?: string;
  requests?: IRequest[];
  requestAssignments?: IRequestAssignment[];
}

export const defaultValue: Readonly<IStatus> = {};
