import { IAbility } from 'app/shared/model/ability.model';

export interface IAbilityCategory {
  id?: number;
  name?: string;
  abilityTypes?: IAbility[];
}

export const defaultValue: Readonly<IAbilityCategory> = {};
