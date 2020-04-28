import { Moment } from 'moment';

export interface IRequestAssignment {
  id?: number;
  startDate?: Moment;
  endDate?: Moment;
  dateCreated?: Moment;
  internRequestAssignmentCode?: string;
  internRequestAssignmentId?: number;
  organizationRequestAssignmentName?: string;
  organizationRequestAssignmentId?: number;
  statusName?: string;
  statusId?: number;
}

export const defaultValue: Readonly<IRequestAssignment> = {};
