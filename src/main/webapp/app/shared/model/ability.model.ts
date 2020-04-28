import { IIntern } from 'app/shared/model/intern.model';
import { IRequest } from 'app/shared/model/request.model';

export interface IAbility {
  id?: number;
  name?: string;
  description?: string;
  typeName?: string;
  typeId?: number;
  interns?: IIntern[];
  requests?: IRequest[];
}

export const defaultValue: Readonly<IAbility> = {};
